package co.kr.sopt_seminar_30th.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import co.kr.sopt_seminar_30th.domain.entity.follower.FollowerInformation
import co.kr.sopt_seminar_30th.presentation.ui.adapter.HomeFollowerAdapter

class MyItemTouchHelperForFollower(
    private val recyclerViewAdapter: HomeFollowerAdapter,
    private val updateData: () -> (Unit),
    private val removeData: (FollowerInformation) -> (Unit)
) :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
        ItemTouchHelper.LEFT
    ) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        recyclerViewAdapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val removedFollower: FollowerInformation =
            recyclerViewAdapter.getItemList()[viewHolder.adapterPosition]
        recyclerViewAdapter.removeItem(viewHolder.adapterPosition)
        removeData(removedFollower)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        updateData()
    }
}