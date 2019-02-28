package com.sournary.materialdesigntutorial.ui.transformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_transformation.*

/**
 * Created: 21/08/2018
 * By: Sang
 * Description:
 */
class TransformationFragment : Fragment() {

    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_transformation, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (fab.isExpanded) fab.isExpanded = false else navController.popBackStack()
        }
        fab.setOnClickListener { fab.isExpanded = !fab.isExpanded }
    }
}