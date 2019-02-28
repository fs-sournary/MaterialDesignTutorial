package com.sournary.materialdesigntutorial.transition

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.transition.Fade
import androidx.transition.SidePropagation
import androidx.transition.TransitionValues
import com.sournary.materialdesigntutorial.transition.interpolator.LINEAR_OUT_SLOW_IN

/**
 * Created in 10/10/2019 by Sang
 */
class Stagger : Fade(IN) {

    init {
        duration = LARGE_EXPAND_DURATION
        interpolator = LINEAR_OUT_SLOW_IN
        propagation = SidePropagation().apply {
            setSide(Gravity.BOTTOM)
            // Set start delay of the propagation. It also relates to the transition duration.
            // If set = 0, there is a IllegalException.
            // If set > 0, for example set = 2, start delay = transition duration / 2.
            // If set < 0, same with set > 0 but it will invert the side of propagation.
            // In this case, transition duration = 300L and set = 1 => start delay = 150L / 1 = 150L
            setPropagationSpeed(1f)
        }
    }

    override fun createAnimator(
        sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?
    ): Animator? {
        val view = startValues?.view ?: endValues?.view ?: return null
        val fadeAnimator = super.createAnimator(sceneRoot, startValues, endValues) ?: return null
        return AnimatorSet().apply {
            playTogether(
                fadeAnimator,
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, view.height * 0.5f, 0f)
            )
        }
    }

    companion object {

        private const val LARGE_EXPAND_DURATION = 1000L
    }
}
