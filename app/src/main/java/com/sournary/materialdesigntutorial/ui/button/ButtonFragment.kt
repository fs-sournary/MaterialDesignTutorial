package com.sournary.materialdesigntutorial.ui.button

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sournary.materialdesigntutorial.R

/**
 * Created: 21/08/2018
 * By: Sang
 * Description:
 */
class ButtonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_button, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Test safeArgs
        val safeArgs: ButtonFragmentArgs by navArgs()
        val number = safeArgs.number
        val text = safeArgs.text
    }
}
