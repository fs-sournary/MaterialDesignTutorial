package com.sournary.materialdesigntutorial.ui.trimmingstrokedpath

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_trimming_stroked_path.*

/**
 * Created in 25/10/2019 by Sang
 */
class TrimmingStrokedPathFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_trimming_stroked_path, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        on_button.setOnClickListener {
            off_button.isEnabled = true
            error_button.isEnabled = true
            image.setImageState(STATE_SET_ON, true)
        }
        off_button.setOnClickListener {
            on_button.isEnabled = true
            error_button.isEnabled = false
            image.setImageState(STATE_SET_OFF, true)
        }
        error_button.setOnClickListener {
            on_button.isEnabled = true
            off_button.isEnabled = true
            image.setImageState(STATE_SET_ERROR, true)
        }
    }

    companion object {

        private val STATE_SET_ON =
            listOf(R.attr.state_on, -R.attr.state_off, -R.attr.state_error).toIntArray()
        private val STATE_SET_OFF =
            listOf(-R.attr.state_on, R.attr.state_off, -R.attr.state_error).toIntArray()
        private val STATE_SET_ERROR =
            listOf(-R.attr.state_on, -R.attr.state_off, R.attr.state_error).toIntArray()
    }
}
