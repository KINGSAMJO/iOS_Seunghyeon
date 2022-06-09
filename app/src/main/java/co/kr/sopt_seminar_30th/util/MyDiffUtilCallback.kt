package co.kr.sopt_seminar_30th.util

import androidx.recyclerview.widget.DiffUtil
import co.kr.sopt_seminar_30th.domain.entity.tmp.follower.FollowerInformation
import co.kr.sopt_seminar_30th.domain.entity.tmp.repository.RepositoryInformation

class MyDiffUtilCallback(private val oldItemList: List<Any>, private val newItemList: List<Any>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItemList.size

    override fun getNewListSize(): Int = newItemList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemList[oldItemPosition]
        val newItem = newItemList[newItemPosition]

        return if(oldItem is FollowerInformation && newItem is FollowerInformation) {
            oldItem.followerName == newItem.followerName
        } else if (oldItem is RepositoryInformation && newItem is RepositoryInformation) {
            oldItem.repositoryName == newItem.repositoryName
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldItemList[oldItemPosition] == newItemList[newItemPosition]
}