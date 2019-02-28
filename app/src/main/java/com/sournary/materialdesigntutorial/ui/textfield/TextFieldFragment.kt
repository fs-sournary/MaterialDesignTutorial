package com.sournary.materialdesigntutorial.ui.textfield

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_text_field.*

/**
 * Created: 27/08/2018
 * By: Sang
 * Description: With TextField, we only should use helper or error text, not both.
 */
class TextFieldFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_text_field, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        filed_edit_text.setOnKeyListener { _, _, _ ->
            val inputText = filed_edit_text.text.toString()
            if (isValidInput(inputText)) {
                filed_box_input_layout.error = null
            } else {
                filed_box_input_layout.error = "You have to fill with least 2 characters"
            }
            false
        }
    }

    private fun isValidInput(text: String?): Boolean = text != null && text.length > 2
}
