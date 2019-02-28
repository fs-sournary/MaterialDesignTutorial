package com.sournary.materialdesigntutorial.ui.card

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.*
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.widget.DraggableCoordinatorLayout
import kotlinx.android.synthetic.main.fragment_draggable_card.*

/**
 * Created in 9/19/19 by Sang
 * Description:
 */
class DraggableCardFragment : BaseCardFragment() {

    override fun getCurrentDestId(): Int = R.id.draggable_card_dest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_draggable_card, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        draggable_card_container.apply {
            addDraggableChild(draggable_card)
            layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            viewDragListener = object : DraggableCoordinatorLayout.ViewDragListener {
                override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
                    draggable_card.isDragged = true
                }

                override fun onViewReleased(releaseChild: View, x: Float, y: Float) {
                    draggable_card.isDragged = false
                }
            }
        }
    }
}
