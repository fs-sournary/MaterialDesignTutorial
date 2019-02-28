package com.sournary.materialdesigntutorial.ui.pathmorphing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_path_morphing.*

/**
 * Created in 31/10/2019 by Sang
 */
class PathMorphingFragment : Fragment() {

    private val viewModel: PathMorphingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_path_morphing, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        plus_minus_image.setOnClickListener {
            val state = if (viewModel.checkedPlusMinus) {
                -1 * android.R.attr.state_checked
            } else {
                android.R.attr.state_checked
            }
            val states = mutableListOf(state).toIntArray()
            plus_minus_image.setImageState(states, true)
            viewModel.checkedPlusMinus = !viewModel.checkedPlusMinus
        }
        overflow_back_image.setOnClickListener {
            val state = if (viewModel.checkedOverflowBack) {
                -1 * android.R.attr.state_checked
            } else {
                android.R.attr.state_checked
            }
            val states = mutableListOf(state).toIntArray()
            overflow_back_image.setImageState(states, true)
            viewModel.checkedOverflowBack = !viewModel.checkedOverflowBack
        }
    }
}
