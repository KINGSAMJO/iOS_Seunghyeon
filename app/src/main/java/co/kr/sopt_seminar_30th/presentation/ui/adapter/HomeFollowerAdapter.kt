package co.kr.sopt_seminar_30th.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ItemHomeFollowerBinding
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation
import co.kr.sopt_seminar_30th.util.MyDiffUtilCallback
import java.util.*

class HomeFollowerAdapter(private val itemClick: (FollowerInformation) -> (Unit)) :
    RecyclerView.Adapter<HomeFollowerAdapter.TestHomeFollowerViewHolder>() {
    private val itemList = mutableListOf<FollowerInformation>()

    class TestHomeFollowerViewHolder(
        private val binding: ItemHomeFollowerBinding,
        private val itemClick: (FollowerInformation) -> (Unit)
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: FollowerInformation) {
            binding.follower = follower

            binding.root.setOnClickListener {
                itemClick(follower)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHomeFollowerViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeFollowerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home_follower,
            parent,
            false
        )
        return TestHomeFollowerViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: TestHomeFollowerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateItemList(newItemList: List<FollowerInformation>?) {
        newItemList?.let {
            val diffCallback = MyDiffUtilCallback(itemList, newItemList)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            itemList.run {
                clear()
                addAll(newItemList)
                diffResult.dispatchUpdatesTo(this@HomeFollowerAdapter)
            }
        }
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        Collections.swap(itemList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }
}