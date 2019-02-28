package com.sournary.materialdesigntutorial.ui.animationmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.AnimationMenuItem
import kotlinx.android.synthetic.main.fragment_animation_menu.*

/**
 * Created on 2019-09-21 by Sang
 * Description:
 **/
class AnimationMenuFragment : Fragment() {

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_animation_menu, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAnimationList()
    }

    private fun setupAnimationList() {
        val animationAdapter = AnimationMenuAdapter(object : AnimationMenuItemListener {
            override fun onClick(position: Int) {
                val navDirections = when (position) {
                    0 -> AnimationMenuFragmentDirections.navigateToDissolve()
                    1 -> AnimationMenuFragmentDirections.navigateToFadeThrough()
                    2 -> AnimationMenuFragmentDirections.navigateToFabTransformation()
                    3 -> AnimationMenuFragmentDirections.navigateToReorderList()
                    4 -> AnimationMenuFragmentDirections.navigateToStaggerList()
                    5 -> AnimationMenuFragmentDirections.navigateToLoadingList()
                    6 -> AnimationMenuFragmentDirections.navigateToNavFadeThrough()
                    7 -> AnimationMenuFragmentDirections.navigateToLoadingList()
                    else -> throw IllegalArgumentException("Unknown position: $position")
                }
                navController.navigate(navDirections)
            }
        })
        animation_recycler.adapter = animationAdapter
        animationAdapter.submitList(AnimationMenuItem.ALL)
    }
}
