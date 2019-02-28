package com.sournary.materialdesigntutorial.ui.groupofpath

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_group_of_path.*

/**
 * Created in 31/10/2019 by Sang
 */
class GroupOfPathFragment : Fragment() {

    private val viewModel: GroupOfPathViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_group_of_path, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        collapse_expand_image.setOnClickListener {
            val states = if (viewModel.expanded) STATE_COLLAPSE else STATE_EXPAND
            collapse_expand_image.setImageState(states, true)
            viewModel.expanded = viewModel.expanded.not()
        }
    }

    companion object {

        private val STATE_EXPAND =
            mutableListOf(R.attr.state_expand, -R.attr.state_collapse).toIntArray()
        private val STATE_COLLAPSE =
            mutableListOf(-R.attr.state_expand, R.attr.state_collapse).toIntArray()
    }
}
