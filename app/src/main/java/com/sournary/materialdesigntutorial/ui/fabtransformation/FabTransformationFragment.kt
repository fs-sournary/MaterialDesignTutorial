package com.sournary.materialdesigntutorial.ui.fabtransformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_fab_transformation.*

/**
 * Created in 10/4/19 by Sang
 * Description:
 */
class FabTransformationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_fab_transformation, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fab.setOnClickListener { fab.isExpanded = true }
        scrim.setOnClickListener { fab.isExpanded = false }
        firstItemText.setOnClickListener { setDescriptionText(firstItemText.text.toString()) }
        secondItemText.setOnClickListener { setDescriptionText(secondItemText.text.toString()) }
        thirdItemText.setOnClickListener { setDescriptionText(thirdItemText.text.toString()) }
        fourItemText.setOnClickListener { setDescriptionText(fourItemText.text.toString()) }
    }

    private fun setDescriptionText(text: String) {
        descriptionText.text = text
        fab.isExpanded = false
    }
}
