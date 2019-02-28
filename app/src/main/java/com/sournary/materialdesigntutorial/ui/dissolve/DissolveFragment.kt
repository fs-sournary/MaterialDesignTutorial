package com.sournary.materialdesigntutorial.ui.dissolve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.transition.TransitionManager
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.transition.Dissolve
import com.sournary.materialdesigntutorial.transition.interpolator.FAST_OUT_SLOW_IN
import kotlinx.android.synthetic.main.fragment_dissolve.*

/**
 * 1, Created in 10/7/19 by Sang
 * 2, Description:
 */
class DissolveFragment : Fragment() {

    private val viewModel: DissolveViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dissolve, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dissolve = Dissolve().apply {
            addTarget(dissolveImage)
            duration = 200L
            interpolator =
                FAST_OUT_SLOW_IN
        }
        viewModel.image.observe(viewLifecycleOwner, Observer {
            TransitionManager.beginDelayedTransition(dissolveCard, dissolve)
            dissolveImage.setImageResource(it)
        })
        nextButton.setOnClickListener { viewModel.nextImage() }
        prevButton.setOnClickListener { viewModel.prevImage() }
    }
}
