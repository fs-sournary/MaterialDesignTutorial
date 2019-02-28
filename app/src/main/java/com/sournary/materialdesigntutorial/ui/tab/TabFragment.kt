package com.sournary.materialdesigntutorial.ui.tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_tab.*

/**
 * Created: 21/08/2018
 * By: Sang
 * Description:
 */
class TabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tab, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        chip_icon.setOnCheckedChangeListener { _, b ->
            setIconTab(tab_no_color, b, R.drawable.ic_star)
            setIconTab(tab_colored, b, R.drawable.ic_star)
        }
        chip_label.setOnCheckedChangeListener { _, b ->
            setLabelTab(tab_no_color, b, R.string.tab_label_tab)
            setLabelTab(tab_colored, b, R.string.tab_label_tab)
        }
        chip_inline.setOnCheckedChangeListener { _, b ->
            tab_no_color.isInlineLabel = b
            tab_colored.isInlineLabel = b
        }
        setupIndicatorChipState(chip_bottom_line)
        setupIndicatorChipState(chip_top_line)
        setupIndicatorChipState(chip_center_line)
        chip_group_indicator.setOnCheckedChangeListener { chipGroup, _ ->
            when (chipGroup.checkedChipId) {
                R.id.chip_bottom_line -> setAllTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_BOTTOM)
                R.id.chip_top_line -> setAllTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_TOP)
                R.id.chip_center_line -> setAllTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_CENTER)
            }
        }
    }

    private fun setIconTab(tabLayout: TabLayout, isShow: Boolean, @DrawableRes id: Int) {
        for (i in 0 until tabLayout.tabCount) {
            if (isShow) {
                tabLayout.getTabAt(i)!!.setIcon(id)
            } else {
                tabLayout.getTabAt(i)!!.icon = null
            }
        }
    }

    private fun setLabelTab(tabLayout: TabLayout, isShow: Boolean, @StringRes id: Int) {
        for (i in 0 until tabLayout.tabCount) {
            if (isShow) {
                val text = getString(id, i + 1)
                tabLayout.getTabAt(i)!!.text = text
            } else {
                tabLayout.getTabAt(i)!!.text = null
            }
        }
    }

    private fun setupIndicatorChipState(chip: Chip) {
        val selectedColor = ContextCompat.getColor(activity!!, R.color.selected_chip_text)
        val unSelectedColor = ContextCompat.getColor(activity!!, android.R.color.black)
        chip.setOnCheckedChangeListener { _, b ->
            Log.d("setIndicatorChipState", "$b")
            if (b) {
                chip.setTextColor(selectedColor)
                chip.setChipBackgroundColorResource(R.color.selected_chip_background)
                chip.setChipStrokeColorResource(R.color.selected_chip_text)
            } else {
                chip.setTextColor(unSelectedColor)
                chip.setChipStrokeColorResource(android.R.color.black)
                chip.setChipBackgroundColorResource(android.R.color.white)
            }
        }
    }

    private fun setAllTabIndicatorGravity(gravity: Int) {
        tab_no_color.setSelectedTabIndicatorGravity(gravity)
        tab_colored.setSelectedTabIndicatorGravity(gravity)
    }

    private fun setAllTabIndicator(@DrawableRes id: Int) {
        tab_no_color.setSelectedTabIndicator(id)
        tab_colored.setSelectedTabIndicator(id)
    }
}
