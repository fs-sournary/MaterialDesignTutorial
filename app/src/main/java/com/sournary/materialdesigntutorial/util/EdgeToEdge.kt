package com.sournary.materialdesigntutorial.util

import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout

/**
 * Created in 15/10/2019 by Sang
 */
object EdgeToEdge :
    EdgeToEdgeCallback by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        EdgeToEdgeApi21()
    } else {
        EdgeToEdgeBase()
    }

private interface EdgeToEdgeCallback {

    /**
     * Configure a root View of an Activity in edge-to-edge display.
     */
    fun setupRoot(root: ViewGroup) {}

    fun setupAppBar(appBar: AppBarLayout, toolbar: Toolbar) {}

    fun setupScrollingContent(scrollingContent: ViewGroup) {}
}

private class EdgeToEdgeBase : EdgeToEdgeCallback

@RequiresApi(21)
private class EdgeToEdgeApi21 : EdgeToEdgeCallback {

    override fun setupRoot(root: ViewGroup) {
        root.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

    override fun setupAppBar(appBar: AppBarLayout, toolbar: Toolbar) {

    }

    override fun setupScrollingContent(scrollingContent: ViewGroup) {

    }
}
