package com.sournary.materialdesigntutorial.transition.interpolator

import android.animation.TimeInterpolator
import androidx.core.view.animation.PathInterpolatorCompat

/**
 *
 *
 * Create custom interpolator that uses [PathInterpolatorCompat]
 */
val FAST_OUT_SLOW_IN: TimeInterpolator by lazy {
    PathInterpolatorCompat.create(0.4f, 0f, 0.2f, 1f)
}

val LINEAR_OUT_SLOW_IN: TimeInterpolator by lazy {
    PathInterpolatorCompat.create(0f, 0f, 0.2f, 1f)
}

/**
 * Accelerate easing.
 *
 * Elements exiting a screen use acceleration easing, where they start at rest and end at peak
 * velocity.
 */
val FAST_OUT_LINEAR_IN: TimeInterpolator by lazy(LazyThreadSafetyMode.NONE) {
    PathInterpolatorCompat.create(0.4f, 0f, 1f, 1f)
}

