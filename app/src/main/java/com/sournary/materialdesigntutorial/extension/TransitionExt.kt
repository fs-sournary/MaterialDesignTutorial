package com.sournary.materialdesigntutorial.extension

import androidx.transition.Transition
import androidx.transition.TransitionSet
import com.sournary.materialdesigntutorial.transition.SequentialTransitionSet

/**
 * Created in 9/19/19 by Sang
 * Description:
 */
inline fun TransitionSet.forEach(action: (transition: Transition) -> Unit) {
    for (i in 0 until transitionCount) {
        action(getTransitionAt(i) ?: throw IndexOutOfBoundsException())
    }
}

inline fun TransitionSet.forEachIndexed(action: (index: Int, transition: Transition) -> Unit) {
    for (i in 0 until transitionCount) {
        action(i, getTransitionAt(i) ?: throw IndexOutOfBoundsException())
    }
}

inline fun transitionTogether(crossinline body: TransitionSet.() -> Unit): TransitionSet =
    TransitionSet().apply {
        ordering = TransitionSet.ORDERING_TOGETHER
        body()
    }

inline fun transitionSequential(
    crossinline body: SequentialTransitionSet.() -> Unit
): SequentialTransitionSet = SequentialTransitionSet().apply(body)

operator fun TransitionSet.plusAssign(transition: Transition) {
    addTransition(transition)
}
