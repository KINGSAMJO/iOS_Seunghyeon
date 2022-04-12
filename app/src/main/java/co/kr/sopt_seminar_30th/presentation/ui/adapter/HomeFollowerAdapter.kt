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

class HomeFollowerAdapter : RecyclerView.Adapter<HomeFollowerAdapter.HomeFollowerViewHolder>() {
    private val asyncDiffer = AsyncListDiffer(this, diffCallback)

    class HomeFollowerViewHolder(
        private val binding: ItemHomeFollowerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: FollowerInformation) {
            binding.follower = follower
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFollowerViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeFollowerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_home_follower,
            parent,
            false
        )
        return HomeFollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFollowerViewHolder, position: Int) {
        holder.bind(asyncDiffer.currentList[position])
    }

    override fun getItemCount(): Int = asyncDiffer.currentList.size

    fun replaceItem(itemList: List<FollowerInformation>) {
        asyncDiffer.submitList(itemList)
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