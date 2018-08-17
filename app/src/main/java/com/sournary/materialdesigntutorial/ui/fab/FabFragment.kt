package com.sournary.materialdesigntutorial.ui.fab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setSupportActionBar
import com.sournary.materialdesigntutorial.util.showSnackbar

/**
 * Created: 22/08/2018
 * By: Sang
 * Description:
 */
class FabFragment : BaseFragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_fab, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupFab()
    }

    private fun setupToolbar() {
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) { title = "Floating action button" }
    }

    private fun setupFab() {
        val noStyleFab = rootView.findViewById<FloatingActionButton>(R.id.fab_no_style)
        val styleFab = rootView.findViewById<FloatingActionButton>(R.id.fab_style)
        val fabStateButton = rootView.findViewById<MaterialButton>(R.id.button_fab_state)
        fabStateButton.setOnClickListener {
            showOrHideFab(noStyleFab, noStyleFab.isShown)
            showOrHideFab(styleFab, styleFab.isShown)
        }
        val fabListener: (View) -> Unit = {
            showSnackbar(message = "Snackbar", isSetAction = true)
        }
        noStyleFab.setOnClickListener(fabListener)
        styleFab.setOnClickListener(fabListener)
    }

    private fun showOrHideFab(fab: FloatingActionButton, isShowing: Boolean) {
        if (isShowing) fab.hide()
        else fab.show()
    }

    override fun onBackPress() {
        NavHostFragment.findNavController(this).popBackStack()
    }
}
