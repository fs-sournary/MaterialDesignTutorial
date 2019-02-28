package com.sournary.materialdesigntutorial.ui.componentmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.ComponentMenuItem

class ComponentMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val thumbnailImage =
        itemView.findViewById<AppCompatImageView>(R.id.card_action_thumbnail_image)
    private val headerText =
        itemView.findViewById<AppCompatTextView>(R.id.card_action_header_text)
    private val subHeadText = itemView.findViewById<AppCompatTextView>(R.id.subhead_text)

    fun bindView(item: ComponentMenuItem, listener: ComponentMenuItemListener) {
        thumbnailImage.setImageResource(item.media)
        headerText.text = item.header
        subHeadText.text = item.subhead
        itemView.setOnClickListener { listener.onItemClick(it, adapterPosition, item) }
    }

    companion object {

        fun create(parent: ViewGroup, @LayoutRes layoutId: Int): ComponentMenuViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
            return ComponentMenuViewHolder(itemView)
        }
    }
}
