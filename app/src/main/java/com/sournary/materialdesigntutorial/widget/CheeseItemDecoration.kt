package com.sournary.materialdesigntutorial.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created in 14/10/2019 by Sang
 */
class CheeseItemDecoration(private val verticalDecoration: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(0, verticalDecoration, 0, 0)
        }
        if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
            outRect.set(0, 0, 0, verticalDecoration)
        }
    }
}
