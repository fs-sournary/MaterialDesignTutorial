package com.sournary.materialdesigntutorial.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.Snackbar
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_swipe_dismiss.*

/**
 * Created in 9/23/19 by Sang
 * Description:
 */
class SwipeDismissFragment : BaseCardFragment() {

    override fun getCurrentDestId(): Int = R.id.swipe_dismiss_dest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_swipe_dismiss, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val swipeDismissBehavior = SwipeDismissBehavior<View>().apply {
            setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END)
            setListener(object : SwipeDismissBehavior.OnDismissListener {
                override fun onDismiss(view: View?) {
                    Snackbar.make(view ?: return, "Card dismiss", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Undo") {
                            resetCard()
                        }.show()
                }

                override fun onDragStateChanged(state: Int) {
                    when(state){
                        SwipeDismissBehavior.STATE_DRAGGING, SwipeDismissBehavior.STATE_SETTLING ->{
                            swipe_dismiss_card.isDragged = true
                        }
                        SwipeDismissBehavior.STATE_IDLE -> swipe_dismiss_card.isDragged = false
                    }
                }
            })
        }
        (swipe_dismiss_card.layoutParams as CoordinatorLayout.LayoutParams).apply {
            behavior = swipeDismissBehavior
        }
    }

    private fun resetCard() {
        swipe_dismiss_card.apply {
            val margin = resources.getDimensionPixelSize(R.dimen.dp_16)
            (layoutParams as CoordinatorLayout.LayoutParams).setMargins(
                margin,margin,margin,margin
            )
            alpha = 1.0f
            requestLayout()
        }
    }
}
