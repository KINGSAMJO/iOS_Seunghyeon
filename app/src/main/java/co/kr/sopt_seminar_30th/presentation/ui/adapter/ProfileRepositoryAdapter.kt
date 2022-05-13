package co.kr.sopt_seminar_30th.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ItemProfileRepositoryBinding
import co.kr.sopt_seminar_30th.domain.entity.home.UserRepositoryInformation
import co.kr.sopt_seminar_30th.domain.entity.tmp.repository.RepositoryInformation
import co.kr.sopt_seminar_30th.util.MyDiffUtilCallback
import java.util.*

class ProfileRepositoryAdapter(private val removeData: (UserRepositoryInformation) -> (Unit)) :
    RecyclerView.Adapter<ProfileRepositoryAdapter.HomeRepositoryViewHolder>() {
    private val itemList = mutableListOf<UserRepositoryInformation>()

    class HomeRepositoryViewHolder(
        private val binding: ItemProfileRepositoryBinding,
        private val removeItem: (Int) -> (Unit),
        private val removeData: (UserRepositoryInformation) -> (Unit)
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: UserRepositoryInformation) {
            binding.repository = repository
            clickDelete(repository)
        }

        private fun clickDelete(repository: UserRepositoryInformation) {
            binding.tvItemDelete.setOnClickListener {
                removeItem(this.adapterPosition)
                removeData(repository)
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
            removeData(it)
        })
    }

    override fun onBindViewHolder(holder: HomeRepositoryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateItemList(newItemList: List<UserRepositoryInformation>?) {
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

    fun getItemList(): List<UserRepositoryInformation> = itemList
}