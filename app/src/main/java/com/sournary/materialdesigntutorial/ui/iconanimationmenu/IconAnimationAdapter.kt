package com.sournary.materialdesigntutorial.ui.iconanimationmenu

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sournary.materialdesigntutorial.model.IconAnimationItem

/**
 * Created in 24/10/2019 by Sang
 */
class IconAnimationAdapter(private val clickEvent: (position: Int) -> Unit) :
    ListAdapter<IconAnimationItem, IconAnimationViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconAnimationViewHolder =
        IconAnimationViewHolder.create(parent, clickEvent)

    override fun onBindViewHolder(holder: IconAnimationViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<IconAnimationItem>() {

            override fun areItemsTheSame(
                oldItem: IconAnimationItem, newItem: IconAnimationItem
            ): Boolean = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: IconAnimationItem, newItem: IconAnimationItem
            ): Boolean = oldItem.title == newItem.title
        }
    }
}
