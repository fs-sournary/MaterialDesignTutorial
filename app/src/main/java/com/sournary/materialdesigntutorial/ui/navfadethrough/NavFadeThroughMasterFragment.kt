package com.sournary.materialdesigntutorial.ui.navfadethrough

import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Explode
import androidx.transition.Slide
import com.google.android.material.appbar.AppBarLayout
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.Cheese
import kotlinx.android.synthetic.main.fragment_nav_fade_through_mater.*
import java.util.concurrent.TimeUnit

/**
 * Created in 15/10/2019 by Sang
 */
class NavFadeThroughMasterFragment : Fragment() {

    private var lastItemBottomPadding = 0
    private val viewModel: NavFadeThroughMasterViewModel by viewModels()

    private lateinit var adapter: CheeseListAdapter
    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Slide(Gravity.TOP).apply {
            mode = Slide.MODE_OUT
            excludeTarget(R.id.cheeseRecycler, true) // Exclude RecyclerView
            addTarget(R.id.toolbar) // Only for Toolbar
        }
        reenterTransition = Explode()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_nav_fade_through_mater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.expectTransition) {
            postponeEnterTransition(500L, TimeUnit.MILLISECONDS)
        }
        setupWindowInsets(view)
        setupCheeseList()
        setupViewModel()
    }

    private fun setupWindowInsets(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view.parent as View) { _, insets ->
            toolbar.updateLayoutParams<AppBarLayout.LayoutParams> {
                topMargin = insets.systemWindowInsetTop
            }
            if (lastItemBottomPadding != insets.systemWindowInsetBottom) {
                lastItemBottomPadding = insets.systemWindowInsetBottom
            }
            insets
        }
    }

    private fun setupCheeseList() {
        adapter = CheeseListAdapter(
            listener = object : CheeseItemListener {
                override fun onClick(cheese: Cheese, extras: FragmentNavigator.Extras) {
                    viewModel.lastSelectedCheese = cheese
                    navController.navigate(
                        NavFadeThroughMasterFragmentDirections.navigateToDetail(cheese), extras
                    )
                }
            },
            glideLoadEnd = { startPostponedEnterTransition() }
        )
        cheeseRecycler.adapter = adapter
        val navFadeThroughItemDecoration = NavFadeThroughItemDecoration(
            verticalDecoration = resources.getDimensionPixelSize(R.dimen.dp_8),
            lastItemBottomPadding = lastItemBottomPadding
        )
        cheeseRecycler.addItemDecoration(navFadeThroughItemDecoration)
    }

    private fun setupViewModel() {
        viewModel.cheese.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private class NavFadeThroughItemDecoration(
        private val verticalDecoration: Int, private val lastItemBottomPadding: Int
    ) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            when (parent.getChildAdapterPosition(view)) {
                state.itemCount - 1 -> {
                    outRect.set(0, verticalDecoration, 0, lastItemBottomPadding)
                }
                else -> {
                    outRect.set(0, verticalDecoration, 0, 0)
                }
            }
        }
    }
}
