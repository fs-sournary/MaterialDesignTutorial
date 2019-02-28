package com.sournary.materialdesigntutorial.ui.animationmenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.AnimationMenuItem

/**
 * Created in 10/4/19 by Sang
 * Description:
 */
class AnimationMenuViewHolder(
    rootView: View, private val listener: AnimationMenuItemListener
) : RecyclerView.ViewHolder(rootView) {

    private val tabTextMargin = rootView.resources.getDimensionPixelSize(R.dimen.dp_8)

    private val titleText = rootView.findViewById<MaterialTextView>(R.id.title_text)
    private val descriptionText = rootView.findViewById<MaterialTextView>(R.id.description_text)
    private val tabLinear = rootView.findViewById<LinearLayout>(R.id.tab_linear)

    fun bindView(item: AnimationMenuItem) {
        titleText.text = item.title
        descriptionText.text = item.description
        item.tabs.forEach {
            val tabText = MaterialTextView(titleText.context).apply {
                text = it
                setTextColor(ContextCompat.getColor(itemView.context, android.R.color.white))
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { marginEnd = tabTextMargin }
                setBackgroundResource(R.drawable.bg_animation_menu_item)
            }
            tabLinear.addView(tabText)
        }
        itemView.setOnClickListener { listener.onClick(adapterPosition) }
    }

    companion object {

        fun create(
            parent: ViewGroup, listener: AnimationMenuItemListener
        ): AnimationMenuViewHolder {
            val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_animation_menu, parent, false)
            return AnimationMenuViewHolder(rootView, listener)
        }
    }
}
