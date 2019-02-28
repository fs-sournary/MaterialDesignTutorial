package com.sournary.materialdesigntutorial.ui.reorderlist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * 1, Created in 10/9/19 by Sang
 * 2, Description:
 */
class ReorderListAdapter(
    private val itemListener: ReorderListItemListener
) : ListAdapter<Cheese, ReorderListViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReorderListViewHolder =
        ReorderListViewHolder.create(parent, itemListener)

    override fun onBindViewHolder(holder: ReorderListViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Cheese>() {
            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem.name == newItem.name
                        && oldItem.image == newItem.image
                        && oldItem.imageWidth == newItem.imageWidth
                        && oldItem.imageHeight == newItem.imageHeight
        }
    }
}
