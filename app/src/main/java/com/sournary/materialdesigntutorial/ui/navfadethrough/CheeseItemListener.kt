package com.sournary.materialdesigntutorial.ui.navfadethrough

import androidx.navigation.fragment.FragmentNavigator
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * Created in 17/10/2019 by Sang
 */
interface CheeseItemListener {

    fun onClick(cheese: Cheese, extras: FragmentNavigator.Extras)
}
