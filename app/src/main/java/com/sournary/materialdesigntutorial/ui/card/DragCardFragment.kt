package com.sournary.materialdesigntutorial.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_drag.*

/**
 * Created in 9/20/19 by Sang
 * Description:
 */
class DragCardFragment : BaseCardFragment() {

    private val viewModel: CardViewModel by viewModels()

    private lateinit var adapter: DragAdapter

    override fun getCurrentDestId(): Int = R.id.drag_card_dest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_drag, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupList()
        setupViewModel()
    }

    private fun setupList() {
        adapter = DragAdapter()
        val itemTouchHelper = ItemTouchHelper(CardItemHelperCallback())
        adapter.itemTouchHelper = itemTouchHelper
        drag_and_drop_list.adapter = adapter
        itemTouchHelper.attachToRecyclerView(drag_and_drop_list)
    }

    private fun setupViewModel() {
        viewModel.dragItems.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private inner class CardItemHelperCallback : ItemTouchHelper.Callback() {

        override fun getMovementFlags(
            recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            viewModel.moveItem(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // Ignored
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
            val itemView = viewHolder?.itemView ?: return
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG && itemView is MaterialCardView) {
                itemView.isDragged = true
            }
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            val itemView = viewHolder.itemView
            if (itemView is MaterialCardView) {
                itemView.isDragged = false
            }
        }
    }
}
