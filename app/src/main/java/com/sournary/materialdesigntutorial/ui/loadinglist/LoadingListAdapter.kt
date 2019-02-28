package com.sournary.materialdesigntutorial.ui.loadinglist

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * Created in 14/10/2019 by Sang
 */
class LoadingListAdapter : PagedListAdapter<Cheese, LoadingListViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadingListViewHolder =
        LoadingListViewHolder.create(parent)

    override fun onBindViewHolder(holder: LoadingListViewHolder, position: Int) {
        val cheese = getItem(position)
        if (cheese == null){
            holder.showPlaceHolder()
        }else {
            holder.bindView(cheese)
        }
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
