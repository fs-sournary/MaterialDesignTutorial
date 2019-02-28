package com.sournary.materialdesigntutorial.ui.loadinglist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created in 14/10/2019 by Sang
 */
class PlaceHolderAdapter : RecyclerView.Adapter<LoadingListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadingListViewHolder =
        LoadingListViewHolder.create(parent)

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: LoadingListViewHolder, position: Int) {
        holder.showPlaceHolder()
    }
}
