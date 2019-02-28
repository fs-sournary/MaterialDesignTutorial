package com.sournary.materialdesigntutorial.ui.animationmenu

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sournary.materialdesigntutorial.model.AnimationMenuItem

/**
 * Created in 10/4/19 by Sang
 * Description:
 */
class AnimationMenuAdapter(
    private val listener: AnimationMenuItemListener
) : ListAdapter<AnimationMenuItem, AnimationMenuViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimationMenuViewHolder =
        AnimationMenuViewHolder.create(parent, listener)

    override fun onBindViewHolder(holder: AnimationMenuViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<AnimationMenuItem>() {
            override fun areItemsTheSame(
                oldItem: AnimationMenuItem, newItem: AnimationMenuItem
            ): Boolean = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: AnimationMenuItem, newItem: AnimationMenuItem
            ): Boolean = oldItem.description == newItem.description && oldItem.tabs == newItem.tabs
        }
    }
}
