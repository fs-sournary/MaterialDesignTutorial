package com.sournary.materialdesigntutorial.ui.iconanimationmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_icon_animation.*

/**
 * Created in 24/10/2019 by Sang
 */
class IconAnimationMenuFragment : Fragment() {

    private val viewModel: IconAnimationViewModel by viewModels()

    private lateinit var adapter: IconAnimationAdapter
    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_icon_animation, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupIconAnimations()
        setupViewModel()
    }

    private fun setupIconAnimations() {
        adapter = IconAnimationAdapter {
            val navDirection = when (it) {
                0 -> IconAnimationMenuFragmentDirections.navigateToTickCross()
                1 -> IconAnimationMenuFragmentDirections.navigateToTrimmingStrokedPath()
                2 -> IconAnimationMenuFragmentDirections.navigateToPathMorphing()
                3 -> IconAnimationMenuFragmentDirections.navigateToGroupOfPath()
                else -> throw IllegalArgumentException("Unknown position: $it")
            }
            navController.navigate(navDirection)
        }
        recycler.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.items.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }
}
