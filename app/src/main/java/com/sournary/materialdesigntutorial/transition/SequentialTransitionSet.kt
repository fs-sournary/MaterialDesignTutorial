package com.sournary.materialdesigntutorial.transition

import android.animation.TimeInterpolator
import androidx.transition.Transition
import androidx.transition.TransitionSet
import com.sournary.materialdesigntutorial.extension.forEach
import com.sournary.materialdesigntutorial.extension.forEachIndexed
import com.sournary.materialdesigntutorial.transition.interpolator.SegmentInterpolator

/**
 * Runs multiple [Transition]s sequentially.
 *
 * Setting a duration to this set will distribute the duration to each child transition based on its
 * weight. Interpolator is also segmented and applied to the transitions.
 *
 * (Background) A normal [TransitionSet] simply sets its properties to child transitions as they
 * are. This can be problematic for sequential transition sets. For example, setting a duration of
 * 300ms means that the entire duration will be the multiple of 300ms and the child count.
 */
class SequentialTransitionSet : TransitionSet() {

    init {
        ordering = ORDERING_SEQUENTIAL
    }

    private var duration: Long = -1
    private var interpolator: TimeInterpolator? = null

    private val weights = mutableListOf<Float>()

    override fun setOrdering(ordering: Int): TransitionSet {
        require(ordering == ORDERING_SEQUENTIAL) { "SequentialTransitionSet only supports ORDERING_SEQUENTIAL" }
        return super.setOrdering(ordering)
    }

    override fun addTransition(transition: Transition): TransitionSet {
        super.addTransition(transition)
        weights += 1f
        distributeDuration()
        distributeInterpolator()
        return this
    }

    override fun setDuration(duration: Long): TransitionSet {
        // Don't call super.
        this.duration = duration
        distributeDuration()
        return this
    }

    override fun getDuration(): Long = duration

    private fun distributeDuration() {
        if (duration < 0) {
            forEach { transition -> transition.duration = -1 }
            return
        }
        val totalWeight = weights.sum()
        forEachIndexed { i, transition ->
            transition.duration = (duration * weights[i] / totalWeight).toLong()
        }
    }

    override fun setInterpolator(interpolator: TimeInterpolator?): TransitionSet {
        // Don't call super.
        this.interpolator = interpolator
        distributeInterpolator()
        return this
    }

    override fun getInterpolator(): TimeInterpolator? = interpolator

    private fun distributeInterpolator() {
        val interpolator = interpolator
        if (interpolator == null) {
            forEach { transition -> transition.interpolator = null }
            return
        }
        val totalWeight = weights.sum()
        var start = 0f
        forEachIndexed { i, transition ->
            val range = weights[i] / totalWeight
            transition.interpolator = SegmentInterpolator(interpolator, start, start + range)
            start += range
        }
    }
}
