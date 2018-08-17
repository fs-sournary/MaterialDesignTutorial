package com.sournary.materialdesigntutorial.ui.bottomnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.ui.BaseFragment
import com.sournary.materialdesigntutorial.util.setSupportActionBar
import com.sournary.materialdesigntutorial.util.showSnackbar

/**
 * Created: 21/08/2018
 * By: Sang
 * Description:
 */
const val MAX_BOTTOM_NAV_ITEM_COUNT = 5

class BottomNavFragment : BaseFragment() {

    private var bottomNavItemVisibleCount = 3

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_bottom_nav, container, false)
        initToolbar()
        return rootView
    }

    private fun initToolbar() {
        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) {}
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val autoChip = rootView.findViewById<Chip>(R.id.chip_auto)
        val selectedChip = rootView.findViewById<Chip>(R.id.chip_selected)
        val labeledChip = rootView.findViewById<Chip>(R.id.chip_labeled)
        val unlabeledChip = rootView.findViewById<Chip>(R.id.chip_unlabeled)
        setupChip(autoChip)
        setupChip(selectedChip)
        setupChip(labeledChip)
        setupChip(unlabeledChip)
        setupBottomNav()
        setupAddItem()
        setupRemoveItem()
        setupChipGroup()
    }

    private fun setupChip(chip: Chip) {
        val selectedChipTextColor =
            ContextCompat.getColor(activity!!, R.color.color_selected_chip_text)
        val unSelectedChipTextColor = ContextCompat.getColor(activity!!, android.R.color.black)
        chip.setOnCheckedChangeListener { _, b ->
            if (b) {
                setChipViewState(
                    chip = chip,
                    chipTextColor = selectedChipTextColor,
                    chipBackgroundColor = R.color.color_selected_chip_background,
                    chipStrokeColor = R.color.color_selected_chip_text
                )
            } else {
                setChipViewState(
                    chip = chip,
                    chipTextColor = unSelectedChipTextColor,
                    chipBackgroundColor = android.R.color.white,
                    chipStrokeColor = android.R.color.black
                )
            }
        }
    }

    private fun setChipViewState(
        chip: Chip, chipTextColor: Int, chipBackgroundColor: Int, chipStrokeColor: Int
    ) {
        chip.setTextColor(chipTextColor)
        chip.setChipBackgroundColorResource(chipBackgroundColor)
        chip.setChipStrokeColorResource(chipStrokeColor)
    }

    private fun setupChipGroup() {
        val chipGroup = rootView.findViewById<ChipGroup>(R.id.chipgroup_bottomnav)
        chipGroup.setOnCheckedChangeListener { group, _ ->
            when (group.checkedChipId) {
                R.id.chip_auto -> bottomNav.labelVisibilityMode =
                        LabelVisibilityMode.LABEL_VISIBILITY_AUTO
                R.id.chip_selected -> bottomNav.labelVisibilityMode =
                        LabelVisibilityMode.LABEL_VISIBILITY_SELECTED
                R.id.chip_labeled -> bottomNav.labelVisibilityMode =
                        LabelVisibilityMode.LABEL_VISIBILITY_LABELED
                R.id.chip_unlabeled -> bottomNav.labelVisibilityMode =
                        LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED
            }
        }
    }

    private fun setupBottomNav() {
        bottomNav = rootView.findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener {
            showSnackbar(it.title.toString())
            return@setOnNavigationItemSelectedListener true
        }
        bottomNav.setOnNavigationItemReselectedListener {
            showSnackbar(it.title.toString())
        }
    }

    private fun setupAddItem() {
        rootView.findViewById<MaterialButton>(R.id.button_add_item).setOnClickListener {
            if (bottomNavItemVisibleCount < MAX_BOTTOM_NAV_ITEM_COUNT) {
                bottomNav.menu.getItem(bottomNavItemVisibleCount).isVisible = true
                bottomNavItemVisibleCount++
            }
        }
    }

    private fun setupRemoveItem() {
        rootView.findViewById<MaterialButton>(R.id.button_remove_item).setOnClickListener {
            if (bottomNavItemVisibleCount > 0) {
                bottomNav.menu.getItem(bottomNavItemVisibleCount - 1).isVisible = false
                bottomNavItemVisibleCount--
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> NavHostFragment.findNavController(this).popBackStack()
        }
        return true
    }

    override fun onBackPress() {
        NavHostFragment.findNavController(this).popBackStack()
    }
}