package com.sournary.materialdesigntutorial.ui.navfadethrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.ViewGroupCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.ChangeBounds
import androidx.transition.ChangeTransform
import androidx.transition.Transition
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.extension.plusAssign
import com.sournary.materialdesigntutorial.extension.transitionTogether
import com.sournary.materialdesigntutorial.transition.interpolator.FAST_OUT_SLOW_IN
import kotlinx.android.synthetic.main.fragment_nav_fade_through_detail.*

/**
 * Created in 15/10/2019 by Sang
 */
class NavFadeThroughDetailFragment : Fragment() {

    private val args: NavFadeThroughDetailFragmentArgs by navArgs()
    private val viewModel: NavFadeThroughDetailViewModel by viewModels()

    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setCheese(args.Cheese)

        sharedElementEnterTransition = createSharedElementTransition()
        sharedElementReturnTransition = createSharedElementTransition()
    }

    private fun createSharedElementTransition(): Transition = transitionTogether {
        duration = 300L
        interpolator = FAST_OUT_SLOW_IN
        this += ChangeBounds()
        this += ChangeTransform()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_nav_fade_through_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWindowInsets(view)
        setupTransition()
        setupViewModel()
        toolbar.setNavigationOnClickListener { navController.popBackStack() }
    }

    private fun setupTransition() {
        ViewCompat.setTransitionName(root, BG_TRANSITION_NAME)
        ViewGroupCompat.setTransitionGroup(root, true)
    }

    private fun setupWindowInsets(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view.parent as View) { _, insets ->
            toolbar.updateLayoutParams<CollapsingToolbarLayout.LayoutParams> {
                topMargin = insets.systemWindowInsetTop
            }
            content.updatePadding(
                left = insets.systemWindowInsetLeft,
                right = insets.systemWindowInsetRight,
                bottom = insets.systemWindowInsetBottom
            )
            insets
        }
    }

    private fun setupViewModel() {
        viewModel.cheese.observe(viewLifecycleOwner, Observer {
            image.setImageResource(it.image)
            name_text.text = it.name
        })
    }

    companion object {

        const val BG_TRANSITION_NAME = "bg"
    }
}
