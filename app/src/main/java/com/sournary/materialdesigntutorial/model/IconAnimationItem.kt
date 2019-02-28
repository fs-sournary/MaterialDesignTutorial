package com.sournary.materialdesigntutorial.model

/**
 * Created in 24/10/2019 by Sang
 */
data class IconAnimationItem(val title: String) {

    companion object {

        val ALL = listOf(
            IconAnimationItem("Tick-cross"),
            IconAnimationItem("Trimming stroked path"),
            IconAnimationItem("Path morphing"),
            IconAnimationItem("Transforming group of paths")
        )
    }
}
