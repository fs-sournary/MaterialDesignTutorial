package com.sournary.materialdesigntutorial.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.transition.TransitionManager
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.transition.fadeThrough
import kotlinx.android.synthetic.main.layout_not_full_length_divider_card.*

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
class ExpandableCardFragment : BaseCardFragment() {

    override fun getCurrentDestId(): Int = R.id.expandable_card_dest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_expandable_card, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
    }

    private fun setupEvents() {
        val fadeThrough = fadeThrough()
        container.setOnClickListener {
            if (full_info_card.isVisible) {
                TransitionManager.beginDelayedTransition(container, fadeThrough.setDuration(250))
                full_info_card.isVisible = false
                only_image_card.isVisible = true
            } else {
                TransitionManager.beginDelayedTransition(container, fadeThrough.setDuration(200))
                full_info_card.isVisible = true
                only_image_card.isVisible = false
            }
        }
    }
}
