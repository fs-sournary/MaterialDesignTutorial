package com.sournary.materialdesigntutorial.ui.staggerlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.transition.Stagger
import com.sournary.materialdesigntutorial.widget.CheeseItemDecoration
import kotlinx.android.synthetic.main.fragment_stagger_list.*

/**
 * 1, Created in 10/9/19 by Sang
 * 2, Description:
 */
class StaggerListFragment : Fragment() {

    private val viewModel: StaggerListViewModel by viewModels()

    private lateinit var adapter: StaggerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_stagger_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupCheeseList()
        setupViewModel()
    }

    private fun setupCheeseList() {
        val verticalDecoration = resources.getDimensionPixelSize(R.dimen.dp_8)
        adapter = StaggerListAdapter()
        cheeseRecycler.adapter = adapter
        cheeseRecycler.addItemDecoration(CheeseItemDecoration(verticalDecoration))
        cheeseRecycler.itemAnimator = object : DefaultItemAnimator() {
            override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
                dispatchAddFinished(holder)
                dispatchAddStarting(holder)
                return false
            }
        }
    }

    private fun setupViewModel() {
        val stagger = Stagger()
        viewModel.cheeses.observe(viewLifecycleOwner, Observer {
            TransitionManager.beginDelayedTransition(cheeseRecycler, stagger)
            adapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_option_stagger_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh_action -> {
                viewModel.empty()
                viewModel.refresh()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
