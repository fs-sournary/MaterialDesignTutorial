package com.sournary.materialdesigntutorial.ui.card

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.sournary.materialdesigntutorial.R

/**
 * Created in 9/20/19 by Sang
 * Description:
 */
class DragViewHolder(
    itemView: View, private val itemTouchHelper: ItemTouchHelper? = null
) : RecyclerView.ViewHolder(itemView) {

    private val pText = itemView.findViewById<MaterialTextView>(R.id.position_text)
    private val dragImage = itemView.findViewById<AppCompatImageView>(R.id.drag_image)

    @SuppressLint("ClickableViewAccessibility")
    fun bindView(text: Int) {
        pText.text = "$text"
        dragImage.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                itemTouchHelper?.startDrag(this@DragViewHolder)
                return@setOnTouchListener true
            }
            return@setOnTouchListener false
        }
    }

    companion object {

        fun create(
            parent: ViewGroup, itemTouchHelper: ItemTouchHelper? = null
        ): DragViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_drag_swipe, parent, false)
            return DragViewHolder(itemView, itemTouchHelper)
        }
    }
}
