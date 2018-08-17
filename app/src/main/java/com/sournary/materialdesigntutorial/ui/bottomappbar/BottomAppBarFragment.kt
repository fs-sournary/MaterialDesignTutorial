package com.sournary.materialdesigntutorial.ui.bottomappbar

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.ToggleButton
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setSupportActionBar

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
class BottomAppBarFragment : BaseFragment() {

    private lateinit var rootView: View
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_bottom_appbar, container, false)
        initToolbar()
        initAppBar()
        initFab()
        initBottomDrawer()
        initToggle()
        initCenterFab()
        initEndFab()
        return rootView
    }

    private fun initToolbar() {
        val toolBar = rootView.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar) {}
        activity?.menuInflater?.inflate(R.menu.option_menu_bottom_appbar, toolBar.menu)
        toolBar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }
    }

    private fun initAppBar() {
        bottomAppBar = rootView.findViewById(R.id.fragment_bottom_appbar)
        setSupportActionBar(bottomAppBar) {}
    }

    private fun initFab() {
        fab = rootView.findViewById(R.id.fragment_fab)
        fab.setOnClickListener {
            Snackbar.make(it, "Snackbar", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun initBottomDrawer() {
        val bottomDrawer = rootView.findViewById<FrameLayout>(R.id.frame_bottom_drawer)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomDrawer)
        bottomSheetBehavior.isHideable = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomAppBar.setNavigationOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun initToggle() {
        val toggleButton = rootView.findViewById<ToggleButton>(R.id.button_fab_state)
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) fab.show()
            else fab.hide()
        }
    }

    private fun initCenterFab() {
        rootView.findViewById<MaterialButton>(R.id.button_center).setOnClickListener {
            bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        }
    }

    private fun initEndFab() {
        rootView.findViewById<MaterialButton>(R.id.button_end).setOnClickListener {
            bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        }
    }

    override fun onBackPress() {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        } else {
            NavHostFragment.findNavController(this).popBackStack()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bottom_appbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Snackbar.make(view!!, item.title, Snackbar.LENGTH_SHORT).show()
        return true
    }
}
