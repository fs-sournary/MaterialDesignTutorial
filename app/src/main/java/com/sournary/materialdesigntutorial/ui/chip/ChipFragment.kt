package com.sournary.materialdesigntutorial.ui.chip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setSupportActionBar

/**
 * Created: 20/08/2018
 * By: Sang
 * Description:
 */
class ChipFragment : BaseFragment() {

    private lateinit var rootView: View
    private lateinit var checkSwitch: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_chip, container, false)
        val groupChoiceChip: ChipGroup = rootView.findViewById(R.id.chip_group_choice)
        val horizontalGroupChoiceChip: ChipGroup =
            rootView.findViewById(R.id.chip_horizontal_group_choice)
        val choiceChip = rootView.findViewById<Chip>(R.id.chip_choice)
        checkSwitch = rootView.findViewById(R.id.switch_check)
        initChipGroup(groupChoiceChip)
        initChipGroup(horizontalGroupChoiceChip)
        initCheckSwitch(groupChoiceChip)
        initCheckSwitch(horizontalGroupChoiceChip)
        initToolbar()
        initChip(choiceChip)
        return rootView
    }

    @LayoutRes
    private fun getChipGroupItem(singleCheck: Boolean): Int =
//        if (singleCheck) R.layout.view_choice_chip else R.layout.view_fillter_chip
        R.layout.view_fillter_chip

    private fun initChipGroup(chipGroup: ChipGroup) {
        chipGroup.removeAllViews()
        val textArray = resources.getStringArray(R.array.cat_chip_group_text_array)
        textArray.forEach {
            val chip = layoutInflater.inflate(
                getChipGroupItem(checkSwitch.isChecked), chipGroup, false
            ) as Chip
            chip.chipText = it
            chipGroup.addView(chip)
        }
    }

    private fun initCheckSwitch(chipGroup: ChipGroup) {
        checkSwitch.setOnCheckedChangeListener { _, b ->
            chipGroup.isSingleSelection = b
            initChipGroup(chipGroup)
        }
    }

    private fun initToolbar() {
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) {}
    }

    private fun initChip(chip: Chip) {
        val selectedColor = ContextCompat.getColor(activity!!, R.color.color_selected_chip_text)
        val unSelectedColor = ContextCompat.getColor(activity!!, android.R.color.black)
        chip.setOnCheckedChangeListener { _, b ->
            if (b) {
                chip.setTextColor(selectedColor)
                chip.setChipBackgroundColorResource(R.color.color_selected_chip_background)
                chip.setChipStrokeColorResource(R.color.color_selected_chip_text)
            } else {
                chip.setTextColor(unSelectedColor)
                chip.setChipBackgroundColorResource(android.R.color.white)
                chip.setChipStrokeColorResource(android.R.color.darker_gray)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPress()
        }
        return true
    }

    override fun onBackPress() {
        NavHostFragment.findNavController(this).popBackStack()
    }
}
