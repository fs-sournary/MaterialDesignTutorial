package com.sournary.materialdesigntutorial.ui.textfield

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setSupportActionBar

/**
 * Created: 27/08/2018
 * By: Sang
 * Description:
 */
class TextFieldFragment : BaseFragment() {

    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_text_field, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) { title = "Text Field" }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> NavHostFragment.findNavController(this).popBackStack()
        }
        return true
    }

    override fun onBackPress() {
        NavHostFragment.findNavController(this).popBackStack()
    }
}
