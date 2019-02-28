package com.sournary.materialdesigntutorial.ui.navfadethrough

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * Created in 14/10/2019 by Sang
 */
class CheeseListAdapter(
    private val listener: CheeseItemListener, private val glideLoadEnd: () -> Unit
) : ListAdapter<Cheese, CheeseViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder =
        CheeseViewHolder.create(parent, listener, glideLoadEnd)

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Cheese>() {
            override fun areItemsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cheese, newItem: Cheese): Boolean =
                oldItem.image == newItem.image && oldItem.name == newItem.name
        }
    }
}
