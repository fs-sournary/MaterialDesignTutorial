package com.sournary.materialdesigntutorial.ui.iconanimationmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.IconAnimationItem

/**
 * Created in 24/10/2019 by Sang
 */
class IconAnimationViewHolder(
    private val rootView: View,
    private val clickEvent: (position: Int) -> Unit
) : RecyclerView.ViewHolder(rootView) {

    private val titleText = rootView.findViewById<MaterialTextView>(R.id.title_text)

    fun bindView(item: IconAnimationItem) {
        titleText.text = item.title
        rootView.setOnClickListener { clickEvent.invoke(adapterPosition) }
    }

    companion object {

        fun create(parent: ViewGroup, clickEvent: (position: Int) -> Unit): IconAnimationViewHolder {
            val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_icon_animation, parent, false)
            return IconAnimationViewHolder(rootView, clickEvent)
        }
    }
}
