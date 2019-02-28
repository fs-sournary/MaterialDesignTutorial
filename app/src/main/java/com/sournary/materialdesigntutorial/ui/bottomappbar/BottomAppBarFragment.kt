package com.sournary.materialdesigntutorial.ui.bottomappbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_bottom_appbar.*

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
class BottomAppBarFragment : Fragment() {

    private var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_bottom_appbar, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (bottomSheetBehavior != null && bottomSheetBehavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
            } else {
                findNavController().popBackStack()
            }
        }
        fab_dest.setOnClickListener { showSnackBar("Fab has been clicked") }
        bottomSheetBehavior = BottomSheetBehavior.from(nav_container).apply {
            isHideable = true
            state = BottomSheetBehavior.STATE_HIDDEN
        }
        bottom_appbar.setNavigationOnClickListener {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
        }
        fab_state_button.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) fab_dest.show() else fab_dest.hide()
        }
        center_button.setOnClickListener {
            bottom_appbar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        }
        end_button.setOnClickListener {
            bottom_appbar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        }
        nav_view.setNavigationItemSelectedListener {
            showSnackBar("${it.title} is clicked")
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
            true
        }
        bottom_appbar.menu.getItem(0).setOnMenuItemClickListener {
            showSnackBar("${it.title} is clicked")
            true
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).apply {
            anchorView = if (fab_dest.visibility == View.VISIBLE) fab_dest else bottom_appbar
        }.show()
    }
}
