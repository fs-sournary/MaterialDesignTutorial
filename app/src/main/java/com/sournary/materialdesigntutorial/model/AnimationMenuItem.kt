package com.sournary.materialdesigntutorial.model

/**
 * Created in 10/4/19 by Sang
 * Description:
 */
data class AnimationMenuItem(val title: String, val description: String, val tabs: List<String>) {

    companion object {

        val ALL: List<AnimationMenuItem> by lazy {
            val results = mutableListOf<AnimationMenuItem>()
            // Layout > Dissolve
            val dissolve = AnimationMenuItem(
                title = "Layout > Dissolve",
                description = "A dissolve creates a smooth transition between elements that completely overlap one another such as photos inside a card or other container. A foreground element fades in (appears) or out (disappear) to show or hide an element behind it",
                tabs = listOf("Transition", "ViewOverlay")
            )
            results.add(dissolve)
            // Layout > Fade through
            val fadeThrough = AnimationMenuItem(
                title = "Layout > Fade through",
                description = "Fade through involves one element fading out completely before a new one fade in. These transitions can be applied to text, icons and other components that don\' perfectly overlap. This technique lets the background show through during a transition, and it can provide continuity between screens when paired with a shared transformation",
                tabs = listOf("Transition")
            )
            results.add(fadeThrough)
            // Layout > Fab transformation
            val fabTransformation = AnimationMenuItem(
                title = "Fab transformation",
                description = "A Fab can transform info a card by changing Fab size and corner radius",
                tabs = listOf("FabTransformationSheetBehavior", "CircularRevealCardView")
            )
            results.add(fabTransformation)
            // List > Reorder
            val reorderList = AnimationMenuItem(
                title = "List > Reorder",
                description = "Motion make it clear when items are selected and what will happen when they are released",
                tabs = listOf("ItemTouchHelper", "ItemAnimator", "ViewPropertyAnimator")
            )
            results.add(reorderList)
            // List > Stagger
            val staggerList = AnimationMenuItem(
                title = "List > Stagger",
                description = "Stagger refers to applying temporal offsets to a group of elements in sequence, like a list. Stagger creates a cascade effect that focuses attention briefly on each item. It can reveal significant content or highlight affordances with a group",
                tabs = listOf("Transition", "SidePropagation")
            )
            results.add(staggerList)
            // List > Loading
            val loadingList = AnimationMenuItem(
                title = "List > Loading",
                description = "Stagger refers to applying temporal offsets to a group of elements in sequence, like a list. Stagger creates a cascade effect that focuses attention briefly on each item. It can reveal significant content or highlight affordances with a group",
                tabs = listOf("ObjectAnimator")
            )
            results.add(loadingList)
            // Navigation > Fade through
            val navFadeThrough = AnimationMenuItem(
                title = "Navigation > Fade through",
                description = "To simplify overlapping motion, consider substituting a focal element with a fade through transition",
                tabs = listOf("Transition", "FragmentNavigator.Extras")
            )
            results.add(navFadeThrough)
            results
        }
    }
}
