package com.sournary.materialdesigntutorial.ui.navfadethrough

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.ViewGroupCompat
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.extension.doOnEnd
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * Created in 14/10/2019 by Sang
 */
class CheeseViewHolder(
    itemView: View, private val listener: CheeseItemListener, private val glideLoadEnd: () -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val context = itemView.context

    private val card = itemView.findViewById<MaterialCardView>(R.id.card)
    private val contentCard = itemView.findViewById<LinearLayout>(R.id.card_content)
    private val supportingVisualImage =
        itemView.findViewById<AppCompatImageView>(R.id.supportingVisualImage)
    private val nameText = itemView.findViewById<MaterialTextView>(R.id.nameText)

    fun bindView(cheese: Cheese) {
        itemView.setBackgroundColor(ContextCompat.getColor(context, cheese.bgColorId))
        Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions().circleCrop())
            .load(cheese.image)
            // If we want to use ChangeImageTransform(), this is very important method.
            // All Loading image libraries such as Glide, Picasso...crop the image before setting it
            // to an ImageView and catching it.
            // But ChangeImageTransform() requires to have the same aspect ratio on both the start
            // and end state of Scene's every ImageView.
            // .dontTransform()
            .doOnEnd { glideLoadEnd.invoke() }
            .into(supportingVisualImage)
        nameText.apply {
            text = cheese.name
            setTextColor(ContextCompat.getColor(context, cheese.textColorId))
        }
        ViewCompat.setTransitionName(card, "card-${cheese.id}")
        ViewCompat.setTransitionName(contentCard, "contentCard-${cheese.id}")
        ViewCompat.setTransitionName(supportingVisualImage, "supportingVisualImage-${cheese.id}")
        ViewCompat.setTransitionName(nameText, "nameText-${cheese.id}")
        ViewGroupCompat.setTransitionGroup(card, true)
        val extras = FragmentNavigatorExtras(
            card to NavFadeThroughDetailFragment.BG_TRANSITION_NAME
        )
        itemView.setOnClickListener { listener.onClick(cheese, extras) }
    }

    companion object {

        fun create(
            parent: ViewGroup, listener: CheeseItemListener, glideLoadEnd: () -> Unit
        ): CheeseViewHolder {
            val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cheese, parent, false)
            return CheeseViewHolder(rootView, listener, glideLoadEnd)
        }
    }
}
