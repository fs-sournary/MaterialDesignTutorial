package com.sournary.materialdesigntutorial.ui.staggerlist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * Created in 10/10/2019 by Sang
 */
class StaggerListAdapter : ListAdapter<Cheese, StaggerListViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggerListViewHolder =
        StaggerListViewHolder.create(parent)

    override fun onBindViewHolder(holder: StaggerListViewHolder, position: Int) {
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