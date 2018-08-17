package com.sournary.materialdesigntutorial.ui.transformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setActivityStatusBarColor
import com.sournary.materialdesigntutorial.util.setSupportActionBar

/**
 * Created: 21/08/2018
 * By: Sang
 * Description:
 */
class TransformationFragment : BaseFragment() {

    private lateinit var rootView: View
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_transformation, container, false)
        fab = rootView.findViewById(R.id.fragment_fab)
        return rootView
    }

    // Truy suất hoặc restore trạng thái
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupFab()
        setupToolbar()
    }

    private fun setupFab() {
        fab.setOnClickListener {
            fab.isExpanded = !fab.isExpanded
            if (fab.isExpanded) setActivityStatusBarColor(R.color.color_crane_purple_900)
        }
    }

    private fun setupToolbar(){
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar){}
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> NavHostFragment.findNavController(this).popBackStack()
        }
        return true
    }

    override fun onBackPress() {
        if (fab.isExpanded) {
            fab.isExpanded = false
            setActivityStatusBarColor(R.color.colorPrimary)
            return
        }
        NavHostFragment.findNavController(this).popBackStack()
    }
}