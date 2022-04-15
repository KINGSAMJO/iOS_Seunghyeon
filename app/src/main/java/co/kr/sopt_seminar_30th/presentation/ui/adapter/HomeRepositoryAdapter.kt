package co.kr.sopt_seminar_30th.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ItemHomeRepositoryBinding
import co.kr.sopt_seminar_30th.domain.entity.repository.RepositoryInformation
import co.kr.sopt_seminar_30th.util.MyDiffUtilCallback
import java.util.*

class HomeRepositoryAdapter: RecyclerView.Adapter<HomeRepositoryAdapter.HomeRepositoryViewHolder>() {
    private val itemList = mutableListOf<RepositoryInformation>()

    class HomeRepositoryViewHolder(
        private val binding: ItemHomeRepositoryBinding,
        private val removeItem: (Int) -> (Unit)
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: RepositoryInformation) {
            binding.repository = repository
            clickDelete()
        }

        private fun clickDelete() {
            binding.tvItemDelete.setOnClickListener {
                removeItem(this.adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRepositoryViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeRepositoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home_repository,
            parent,
            false
        )
        return HomeRepositoryViewHolder(binding) {
            removeItem(it)
        }
    }

    override fun onBindViewHolder(holder: HomeRepositoryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateItemList(newItemList: List<RepositoryInformation>?) {
        newItemList?.let {
            val diffCallback = MyDiffUtilCallback(itemList, newItemList)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            itemList.run {
                clear()
                addAll(newItemList)
                diffResult.dispatchUpdatesTo(this@HomeRepositoryAdapter)
            }
        }
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        Collections.swap(itemList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    private fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }
}