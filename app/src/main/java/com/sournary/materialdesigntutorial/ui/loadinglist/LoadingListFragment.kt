package com.sournary.materialdesigntutorial.ui.loadinglist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.transition.fade
import com.sournary.materialdesigntutorial.widget.CheeseItemDecoration
import kotlinx.android.synthetic.main.fragment_loading_list.*

/**
 * Created in 14/10/2019 by Sang
 */
class LoadingListFragment : Fragment() {

    private val viewModel: LoadingListViewModel by viewModels()

    private var savedItemAnimator: RecyclerView.ItemAnimator? = null
    private val placeHolderAdapter = PlaceHolderAdapter()
    private val loadingListAdapter = LoadingListAdapter()
    private val fade = fade {
        savedItemAnimator?.apply { cheeseRecycler.itemAnimator = this }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_loading_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLoadingList()
        setupViewModel()
    }

    private fun setupLoadingList() {
        val verticalDecoration = resources.getDimensionPixelSize(R.dimen.dp_8)
        cheeseRecycler.adapter = placeHolderAdapter
        cheeseRecycler.addItemDecoration(CheeseItemDecoration(verticalDecoration))
    }

    private fun setupViewModel() {
        viewModel.cheese.observe(viewLifecycleOwner, Observer {
            if (cheeseRecycler.adapter != loadingListAdapter) {
                cheeseRecycler.adapter = loadingListAdapter
                savedItemAnimator = cheeseRecycler.itemAnimator
                cheeseRecycler.itemAnimator = null
                TransitionManager.beginDelayedTransition(cheeseRecycler, fade)
            }
            loadingListAdapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_loading_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh_action -> {
                TransitionManager.beginDelayedTransition(cheeseRecycler, fade)
                cheeseRecycler.adapter = placeHolderAdapter
                viewModel.refresh()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
