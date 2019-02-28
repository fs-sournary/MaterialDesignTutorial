package com.sournary.materialdesigntutorial.ui.loadinglist

import android.animation.ObjectAnimator
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textview.MaterialTextView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * Created in 14/10/2019 by Sang
 */
class LoadingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val context = itemView.context
    private val animator = ObjectAnimator.ofFloat(itemView, View.ALPHA, 1f, 0f, 1f).apply {
        repeatCount = ObjectAnimator.INFINITE
        duration = 1000L
        doOnEnd { itemView.alpha = 1f }
    }

    private val supportingVisualImage =
        itemView.findViewById<AppCompatImageView>(R.id.supportingVisualImage)
    private val nameText = itemView.findViewById<MaterialTextView>(R.id.nameText)

    fun showPlaceHolder() {
        animator.apply {
            currentPlayTime = (SystemClock.elapsedRealtime() - adapterPosition * 30L) % 1000L
            start()
        }
        loadCircleImage(R.drawable.bg_img_place_holder)
        nameText.apply {
            text = null
            setBackgroundResource(R.drawable.bg_text_place_holder)
        }
    }

    fun bindView(cheese: Cheese) {
        animator.end()
        loadCircleImage(cheese.image)
        nameText.apply {
            text = cheese.name
            setBackgroundResource(0)
        }
    }

    private fun loadCircleImage(image: Int) {
        Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions().circleCrop())
            .load(image)
            .into(supportingVisualImage)
    }

    companion object {

        fun create(parent: ViewGroup): LoadingListViewHolder {
            val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_loading_list, parent, false)
            return LoadingListViewHolder(rootView)
        }
    }
}
