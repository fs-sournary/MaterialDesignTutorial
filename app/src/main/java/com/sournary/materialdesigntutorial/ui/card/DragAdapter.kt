package com.sournary.materialdesigntutorial.ui.card

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter

/**
 * Created in 9/20/19 by Sang
 * Description:
 */
class DragAdapter : ListAdapter<Int, DragViewHolder>(COMPARATOR) {

    var itemTouchHelper: ItemTouchHelper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragViewHolder =
        DragViewHolder.create(parent, itemTouchHelper)

    override fun onBindViewHolder(holder: DragViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean =
                oldItem == newItem
        }
    }
}
