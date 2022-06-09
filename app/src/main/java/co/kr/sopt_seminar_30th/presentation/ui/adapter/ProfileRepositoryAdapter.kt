package co.kr.sopt_seminar_30th.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ItemProfileRepositoryBinding
import co.kr.sopt_seminar_30th.domain.entity.home.UserRepository
import co.kr.sopt_seminar_30th.util.MyDiffUtilCallback
import java.util.*

class ProfileRepositoryAdapter(private val onRemoveData: (UserRepository) -> (Unit)) :
    RecyclerView.Adapter<ProfileRepositoryAdapter.HomeRepositoryViewHolder>() {
    private val itemList = mutableListOf<UserRepository>()

    class HomeRepositoryViewHolder(
        private val binding: ItemProfileRepositoryBinding,
        private val onRemoveItem: (Int) -> (Unit),
        private val onRemoveData: (UserRepository) -> (Unit)
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: UserRepository) {
            binding.repository = repository
            clickDelete(repository)
        }

        private fun clickDelete(repository: UserRepository) {
            binding.tvItemDelete.setOnClickListener {
                onRemoveItem(this.adapterPosition)
                onRemoveData(repository)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRepositoryViewHolder {
        val binding = DataBindingUtil.inflate<ItemProfileRepositoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_profile_repository,
            parent,
            false
        )
        return HomeRepositoryViewHolder(binding, {
            removeItem(it)
        }, {
            onRemoveData(it)
        })
    }

    override fun onBindViewHolder(holder: HomeRepositoryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateItemList(newItemList: List<UserRepository>?) {
        newItemList?.let {
            val diffCallback = MyDiffUtilCallback(itemList, newItemList)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            itemList.run {
                clear()
                addAll(newItemList)
                diffResult.dispatchUpdatesTo(this@ProfileRepositoryAdapter)
            }
        }
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        Collections.swap(itemList, fromPosition, toPosition)
        itemList[fromPosition].repositoryOrder = itemList[toPosition].repositoryOrder.also {
            itemList[toPosition].repositoryOrder = itemList[fromPosition].repositoryOrder
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    private fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItemList(): List<UserRepository> = itemList
}