package com.sournary.materialdesigntutorial.ui.staggerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textview.MaterialTextView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * Created in 10/10/2019 by Sang
 */
class StaggerListViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

    private val supportingVisualImage =
        rootView.findViewById<AppCompatImageView>(R.id.supportingVisualImage)
    private val nameText = rootView.findViewById<MaterialTextView>(R.id.nameText)

    fun bindView(cheese: Cheese) {
        Glide.with(rootView.context)
            .applyDefaultRequestOptions(RequestOptions().circleCrop())
            .load(cheese.image)
            .into(supportingVisualImage)
        nameText.text = cheese.name
    }

    companion object {

        fun create(parent: ViewGroup): StaggerListViewHolder {
            val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_stagger_list, parent, false)
            return StaggerListViewHolder(rootView)
        }
    }
}
