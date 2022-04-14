package co.kr.sopt_seminar_30th.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView

class MyItemDecoration(private val myOffset: Int, private val myRound: Int, private val myColor: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val offset = myOffset.dpToPx()
        outRect.set(offset, offset, offset, offset)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val strokeWidth = 5
        val paint = Paint().apply {
            this.color = myColor
            this.style = Paint.Style.STROKE
            this.strokeWidth = strokeWidth.dpToPx().toFloat()
        }

        parent.children.forEach { child ->
            c.drawRoundRect(
                (child.left).toFloat(),
                (child.top).toFloat(),
                (child.right).toFloat(),
                (child.bottom).toFloat(),
                myRound.dpToPx().toFloat(),
                myRound.dpToPx().toFloat(),
                paint
            )
        }
    }
}