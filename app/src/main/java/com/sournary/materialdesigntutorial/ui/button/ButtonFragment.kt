package com.sournary.materialdesigntutorial.ui.button

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setSupportActionBar

/**
 * Created: 21/08/2018
 * By: Sang
 * Description:
 */
class ButtonFragment : BaseFragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_button, container, false)
        initToolbar()
        return rootView
    }

    private fun initToolbar() {
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) {}
    }

    override fun onBackPress() {
        NavHostFragment.findNavController(this).popBackStack()
    }
}
