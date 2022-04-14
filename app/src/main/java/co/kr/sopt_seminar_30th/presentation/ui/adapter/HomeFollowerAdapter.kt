package co.kr.sopt_seminar_30th.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.kr.sopt_seminar_30th.R
import co.kr.sopt_seminar_30th.databinding.ItemHomeFollowerBinding
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation
import timber.log.Timber
import java.util.*

class HomeFollowerAdapter(private val itemClick: (FollowerInformation) -> (Unit)) :
    RecyclerView.Adapter<HomeFollowerAdapter.HomeFollowerViewHolder>() {
    // TODO: RecyclerView Item Move 시 갱신방법에 대한 고찰 필요
    private val asyncDiffer = AsyncListDiffer(this, diffCallback)
    private var modifiedList = mutableListOf<FollowerInformation>()

    class HomeFollowerViewHolder(
        private val binding: ItemHomeFollowerBinding,
        private val itemClick: (FollowerInformation) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: FollowerInformation) {
            binding.follower = follower
            binding.root.setOnClickListener {
                itemClick(follower)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFollowerViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeFollowerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home_follower,
            parent,
            false
        )
        return HomeFollowerViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HomeFollowerViewHolder, position: Int) {
        holder.bind(asyncDiffer.currentList[position])
    }

    override fun getItemCount(): Int = asyncDiffer.currentList.size

    fun replaceItem(itemList: List<FollowerInformation>) {
        asyncDiffer.submitList(itemList)
        modifiedList = asyncDiffer.currentList.toMutableList()
    }

    fun replaceItemAfterItemTouch() {
        asyncDiffer.submitList(modifiedList)
        modifiedList = asyncDiffer.currentList.toMutableList()
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        Collections.swap(modifiedList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun removeItem(position: Int) {
        modifiedList.removeAt(position)
        notifyItemRemoved(position)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<FollowerInformation>() {
            override fun areItemsTheSame(
                oldItem: FollowerInformation,
                newItem: FollowerInformation
            ): Boolean = oldItem.followerName == newItem.followerName

            override fun areContentsTheSame(
                oldItem: FollowerInformation,
                newItem: FollowerInformation
            ): Boolean = oldItem == newItem
        }
    }
}