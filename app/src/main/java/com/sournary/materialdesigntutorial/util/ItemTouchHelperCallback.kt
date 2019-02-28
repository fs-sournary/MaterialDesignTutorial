package com.sournary.materialdesigntutorial.util

import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * Created in 14/10/2019 by Sang
 */
class ItemTouchHelperCallback(
    private val onMove: (start: RecyclerView.ViewHolder, end: RecyclerView.ViewHolder) -> Unit,
    private val onSelectedChanged: (itemView: View, action: Int) -> Unit,
    private val clearView: (itemView: View) -> Unit
) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
    ): Int = makeMovementFlags(
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        0
    )

    override fun onMove(
        recyclerView: RecyclerView, start: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
    ): Boolean {
        onMove.invoke(start, target)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // Ignore
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        val itemView = viewHolder?.itemView ?: return
        onSelectedChanged.invoke(itemView, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        clearView.invoke(viewHolder.itemView)
    }
}
