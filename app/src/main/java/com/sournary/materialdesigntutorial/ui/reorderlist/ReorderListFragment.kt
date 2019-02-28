package com.sournary.materialdesigntutorial.ui.reorderlist

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.Cheese
import com.sournary.materialdesigntutorial.util.ItemTouchHelperCallback
import kotlinx.android.synthetic.main.fragment_reorder_list.*

/**
 * 1, Created in 10/9/19 by Sang
 * 2, Description:
 */
class ReorderListFragment : Fragment() {

    private val viewModel: ReorderListViewModel by viewModels()

    private lateinit var adapter: ReorderListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_reorder_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupCheeseList()
        setupViewModel()
    }

    private fun setupCheeseList() {
        val itemTouchHelper = ItemTouchHelper(getItemHelperCallback())
        itemTouchHelper.attachToRecyclerView(list)
        adapter = ReorderListAdapter(object : ReorderListItemListener {
            override fun onLongClick(
                cheese: Cheese, position: Int, holder: ReorderListViewHolder
            ): Boolean {
                itemTouchHelper.startDrag(holder)
                return true
            }
        })
        val itemSpacing = resources.getDimensionPixelSize(R.dimen.dp_8)
        list.adapter = adapter
        list.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
            ) {
                outRect.set(itemSpacing, itemSpacing, itemSpacing, itemSpacing)
            }
        })
    }

    private fun getItemHelperCallback() = ItemTouchHelperCallback(
        onMove = { start, end -> viewModel.moveCheese(start.adapterPosition, end.adapterPosition) },
        onSelectedChanged = { itemView, action ->
            if (action == ItemTouchHelper.ACTION_STATE_DRAG && itemView is MaterialCardView) {
                itemView.isDragged = true
            }
        },
        clearView = { itemView -> if (itemView is MaterialCardView) itemView.isDragged = false }
    )

    private fun setupViewModel() {
        viewModel.cheeses.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
    }
}
