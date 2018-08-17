package com.sournary.materialdesigntutorial.ui.tab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayout
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setSupportActionBar

/**
 * Created: 21/08/2018
 * By: Sang
 * Description:
 */
class TabFragment : BaseFragment() {

    private lateinit var rootView: View
    private lateinit var noTabLayout: TabLayout
    private lateinit var colorTabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_tab, container, false)
        noTabLayout = rootView.findViewById(R.id.tab_no_color)
        colorTabLayout = rootView.findViewById(R.id.tab_colored)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupIconChip()
        setupLabelChip()
        setupInlineChip()
        setIndicatorChipState(rootView.findViewById(R.id.chip_bottom_line))
        setIndicatorChipState(rootView.findViewById(R.id.chip_top_line))
        setIndicatorChipState(rootView.findViewById(R.id.chip_center_line))
        setupIndicatorChipGroup()
    }

    private fun setupToolbar() {
        val toolbarView = rootView.findViewById<View>(R.id.toolbar)
        val toolbar = toolbarView.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) { title = "Tab" }
    }

    private fun setupIconChip() {
        val iconChip = rootView.findViewById<Chip>(R.id.chip_icon)
        iconChip.setOnCheckedChangeListener { _, b ->
            setIconTab(noTabLayout, b, R.drawable.ic_star)
            setIconTab(colorTabLayout, b, R.drawable.ic_star)
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

    private fun setupLabelChip() {
        val labelChip = rootView.findViewById<Chip>(R.id.chip_label)
        labelChip.setOnCheckedChangeListener { _, b ->
            setLabelTab(noTabLayout, b, R.string.tab_label_tab)
            setLabelTab(colorTabLayout, b, R.string.tab_label_tab)
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

    private fun setupInlineChip() {
        val inlineChip = rootView.findViewById<Chip>(R.id.chip_inline)
        inlineChip.setOnCheckedChangeListener { _, b ->
            noTabLayout.isInlineLabel = b
            colorTabLayout.isInlineLabel = b
        }
    }

    private fun setIndicatorChipState(chip: Chip) {
        val selectedColor = ContextCompat.getColor(activity!!, R.color.color_selected_chip_text)
        val unSelectedColor = ContextCompat.getColor(activity!!, android.R.color.black)
        chip.setOnCheckedChangeListener { _, b ->
            Log.d("setIndicatorChipState", "$b")
            if (b) {
                chip.setTextColor(selectedColor)
                chip.setChipBackgroundColorResource(R.color.color_selected_chip_background)
                chip.setChipStrokeColorResource(R.color.color_selected_chip_text)
            } else {
                chip.setTextColor(unSelectedColor)
                chip.setChipStrokeColorResource(android.R.color.black)
                chip.setChipBackgroundColorResource(android.R.color.white)
            }
        }
    }

    /**
     * Event check chỉ dành cho những Group được setup single selection
     */
    private fun setupIndicatorChipGroup() {
        val indicatorChipGroup = rootView.findViewById<ChipGroup>(R.id.chip_group_indicator)
        indicatorChipGroup.setOnCheckedChangeListener { chipGroup, _ ->
            when (chipGroup.checkedChipId) {
                R.id.chip_bottom_line -> setAllTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_BOTTOM)
                R.id.chip_top_line -> setAllTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_TOP)
                R.id.chip_center_line -> setAllTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_CENTER)
            }
        }
    }

    private fun setAllTabIndicatorGravity(gravity: Int) {
        noTabLayout.setSelectedTabIndicatorGravity(gravity)
        colorTabLayout.setSelectedTabIndicatorGravity(gravity)
    }

    private fun setAllTabIndicator(@DrawableRes id: Int) {
        noTabLayout.setSelectedTabIndicator(id)
        colorTabLayout.setSelectedTabIndicator(id)
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
