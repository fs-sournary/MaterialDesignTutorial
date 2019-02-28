package com.sournary.materialdesigntutorial.transition.interpolator

import android.animation.TimeInterpolator

/**
 * Created in 9/19/19 by Sang
 * Description:
 * - Take [base] Interpolator and extracts (tách) out a segment from it as a new [TimeInterpolator].
 * - This is useful for sequential animations where each of child animations should be interpolated.
 * - So that they match with another animation when combined.
 */
class SegmentInterpolator(
    private val base: TimeInterpolator, private val start: Float = 0f, end: Float = 1f
) : TimeInterpolator {

    private val offset = base.getInterpolation(start)
    private val xRatio = (end - start) / 1f
    private val yRatio = (base.getInterpolation(end) - offset) / 1f

    /**
     * @param [input]: indicate current point in the animation where 0 <=> start and 1 <=> end.
     * @return: The interpolator value in the [input] point in the animation.
     */
    override fun getInterpolation(input: Float): Float =
        (base.getInterpolation(start + input * xRatio) - offset) / yRatio
}
