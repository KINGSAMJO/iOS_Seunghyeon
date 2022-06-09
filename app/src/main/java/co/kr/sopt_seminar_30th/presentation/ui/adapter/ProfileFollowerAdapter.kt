package co.kr.sopt_seminar_30th.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ItemProfileFollowerBinding
import co.kr.sopt_seminar_30th.domain.entity.home.UserFollow
import co.kr.sopt_seminar_30th.util.MyDiffUtilCallback
import timber.log.Timber

class ProfileFollowerAdapter(private val onItemClick: (UserFollow) -> (Unit)) :
    RecyclerView.Adapter<ProfileFollowerAdapter.HomeFollowerViewHolder>() {
    private val itemList = mutableListOf<UserFollow>()

    class HomeFollowerViewHolder(
        private val binding: ItemProfileFollowerBinding,
        private val onItemClick: (UserFollow) -> (Unit)
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: UserFollow) {
            binding.follower = follower

            binding.root.setOnClickListener {
                onItemClick(follower)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFollowerViewHolder {
        val binding = DataBindingUtil.inflate<ItemProfileFollowerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_profile_follower,
            parent,
            false
        )
        return HomeFollowerViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: HomeFollowerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateItemList(newItemList: List<UserFollow>?) {
        newItemList?.let {
            val diffCallback = MyDiffUtilCallback(itemList, newItemList)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            itemList.run {
                clear()
                addAll(newItemList)
                diffResult.dispatchUpdatesTo(this@ProfileFollowerAdapter)
            }
        }
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        itemList[fromPosition].followOrder = itemList[toPosition].followOrder.also {
            if (fromPosition < toPosition) {
                for (i in toPosition downTo fromPosition + 1) {
                    itemList[i].followOrder = itemList[i - 1].followOrder
                }
                itemList.add(toPosition, itemList.removeAt(fromPosition))
            } else if (toPosition < fromPosition) {
                for (i in toPosition until fromPosition) {
                    itemList[i].followOrder = itemList[i + 1].followOrder
                }
                itemList.add(toPosition, itemList.removeAt(fromPosition))
            }
        }
        Timber.i(itemList.toString())
        notifyItemMoved(fromPosition, toPosition)
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItemList(): List<UserFollow> = itemList
}