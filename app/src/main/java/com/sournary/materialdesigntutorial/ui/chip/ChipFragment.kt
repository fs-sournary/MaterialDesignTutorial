package com.sournary.materialdesigntutorial.ui.chip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.extension.showSnackbar
import kotlinx.android.synthetic.main.fragment_chip.*

/**
 * Created: 20/08/2018
 * By: Sang
 * Description:
 */
class ChipFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_chip, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupChipGroup(choice_chip_group)
        setupChipGroup(horizontal_choice_chip_group)
        entry_type_chip.setOnCheckedChangeListener { _, _ -> showSnackbar("Entry Chip") }
        filter_type_chip.setOnCheckedChangeListener { _, _ -> showSnackbar("Filter chip") }
        choice_type_chip.setOnCheckedChangeListener { _, _ -> showSnackbar("Choice chip") }
        action_type_chip.setOnClickListener { showSnackbar("Click action chip") }
        check_switch.setOnCheckedChangeListener { _, _ ->
            setupChipGroup(choice_chip_group)
            setupChipGroup(horizontal_choice_chip_group)
        }
    }

    private fun setupChipGroup(chipGroup: ChipGroup) {
        chipGroup.removeAllViews()
        val checked = check_switch.isChecked
        val layout = if (checked) R.layout.layout_choice_chip else R.layout.layout_fillter_chip
        chipGroup.isSingleSelection = checked
        val textArray = resources.getStringArray(R.array.cat_chip_group_text_array)
        textArray.forEach {
            val chip = layoutInflater.inflate(layout, chipGroup, false) as Chip
            chip.text = it
            chip.isCloseIconVisible = true
            chip.setOnCloseIconClickListener { chipGroup.removeView(chip) }
            chipGroup.addView(chip)
        }
    }
}
