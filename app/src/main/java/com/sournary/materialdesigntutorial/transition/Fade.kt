package com.sournary.materialdesigntutorial.transition

import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionListenerAdapter
import com.sournary.materialdesigntutorial.extension.plusAssign
import com.sournary.materialdesigntutorial.extension.transitionSequential
import com.sournary.materialdesigntutorial.extension.transitionTogether
import com.sournary.materialdesigntutorial.transition.interpolator.FAST_OUT_SLOW_IN

/**
 * 1, Created in 10/8/19 by Sang
 *
 * Tweening motion
 *
 * Link: https://material.io/design/motion/understanding-motion.html#expressing-continuity
 */
fun fadeThrough(): Transition = transitionTogether {
    interpolator = FAST_OUT_SLOW_IN
    this += ChangeBounds()
    this += transitionSequential {
        this += Fade(Fade.OUT)
        this += Fade(Fade.IN)
    }
}

fun fade(end: () -> Unit): Transition = transitionSequential {
    interpolator = FAST_OUT_SLOW_IN
    this += Fade(Fade.OUT)
    this += Fade(Fade.IN)
    addListener(object : TransitionListenerAdapter() {
        override fun onTransitionEnd(transition: Transition) {
            end.invoke()
        }
    })
}
