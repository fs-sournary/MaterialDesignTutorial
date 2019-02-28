package com.sournary.materialdesigntutorial.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.customview.widget.ViewDragHelper

/**
 * Created in 9/19/19 by Sang
 */
class DraggableCoordinatorLayout : CoordinatorLayout {

    var viewDragListener: ViewDragListener? = null
    private val dragCallback = object : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean =
            child.isVisible && isDraggableChild(child)

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            viewDragListener?.onViewCaptured(capturedChild, activePointerId)
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            viewDragListener?.onViewReleased(releasedChild, xvel, yvel)
        }

        override fun getViewHorizontalDragRange(child: View): Int = child.width

        override fun getViewVerticalDragRange(child: View): Int = child.height

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int = left

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int = top
    }

    private val viewDragHelper: ViewDragHelper = ViewDragHelper.create(this, dragCallback)
    private val draggableChildes = mutableListOf<View>()

    constructor(context: Context) : super(context, null)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private fun isDraggableChild(child: View): Boolean =
        draggableChildes.isNotEmpty() && draggableChildes.contains(child)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return viewDragHelper.shouldInterceptTouchEvent(
            ev ?: return false
        ) || super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        viewDragHelper.processTouchEvent(ev ?: return false)
        return super.onTouchEvent(ev)
    }

    fun addDraggableChild(child: View) {
        if (child.parent == this) {
            draggableChildes.add(child)
        }
    }

    interface ViewDragListener {

        fun onViewCaptured(capturedChild: View, activePointerId: Int)

        fun onViewReleased(releaseChild: View, x: Float, y: Float)
    }
}
