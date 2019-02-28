package com.sournary.materialdesigntutorial.transition

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.core.view.drawToBitmap
import androidx.transition.Transition
import androidx.transition.TransitionValues

/**
 * 1, Created in 10/7/19 by Sang
 * 2, Description:
 * Dissolve transition is a type of fading that refers to tweening an element's opacity.
 * Dissolve creates a smooth transition between elements that completely overlap one another.
 * Example: image in a Card or a container.
 * Link: https://material.io/design/motion/understanding-motion.html#expressing-continuity
 */
class Dissolve : Transition() {

    override fun captureStartValues(transitionValues: TransitionValues) {
        transitionValues.values[BITMAP_NAME_PROP] = transitionValues.view.drawToBitmap()
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        transitionValues.values[BITMAP_NAME_PROP] = transitionValues.view.drawToBitmap()
    }

    override fun createAnimator(
        sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?
    ): Animator? {
        if (startValues == null || endValues == null) {
            return null
        }
        val startBitmap = startValues.values[BITMAP_NAME_PROP] as Bitmap
        val endBitmap = endValues.values[BITMAP_NAME_PROP] as Bitmap
        if (startBitmap.sameAs(endBitmap)) {
            return null
        }
        val view = endValues.view
        val startDrawable = BitmapDrawable(view.resources, startBitmap).apply {
            setBounds(0, 0, startBitmap.width, startBitmap.height)
        }

        val viewOverlay = view.overlay
        viewOverlay.add(startDrawable)

        return ObjectAnimator.ofInt(startDrawable, "alpha", 255, 0).apply {
            doOnEnd { viewOverlay.remove(startDrawable) }
        }
    }

    companion object {

        private const val BITMAP_NAME_PROP = "com.sournary.materialtutorial.dissolve.bitmap"
    }
}
