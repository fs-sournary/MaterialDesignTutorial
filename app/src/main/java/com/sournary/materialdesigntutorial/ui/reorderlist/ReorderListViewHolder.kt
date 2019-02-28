package com.sournary.materialdesigntutorial.ui.reorderlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * 1, Created in 10/9/19 by Sang
 * 2, Description:
 */
class ReorderListViewHolder(
    private val rootView: View, private val listener: ReorderListItemListener
) : RecyclerView.ViewHolder(rootView) {

    private val titleText = rootView.findViewById<TextView>(R.id.titleText)
    private val avatarImage = rootView.findViewById<ImageView>(R.id.avatarImage)
    private val constraintLayout = rootView.findViewById<ConstraintLayout>(R.id.constraint)
    private val constraintSet = ConstraintSet().apply { clone(constraintLayout) }
    private val titleFormat = rootView.resources.getString(R.string.list_reorder_item_title_format)

    fun bindView(cheese: Cheese) {
        titleText.text = String.format(titleFormat, adapterPosition, cheese.name)
        avatarImage.setImageResource(cheese.image)
        constraintSet.setDimensionRatio(
            R.id.avatarImage, "H,${cheese.imageWidth}:${cheese.imageHeight}"
        )
        constraintSet.applyTo(constraintLayout)
        rootView.setOnLongClickListener { listener.onLongClick(cheese, adapterPosition, this) }
    }

    companion object {

        fun create(parent: ViewGroup, listener: ReorderListItemListener): ReorderListViewHolder {
            val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_reorder, parent, false)
            return ReorderListViewHolder(rootView, listener)
        }
    }
}
