package com.sournary.materialdesigntutorial.ui.bottomnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.sournary.materialdesigntutorial.R
import kotlinx.android.synthetic.main.fragment_bottom_nav.*

/**
 * Created: 21/08/2018
 * By: Sang
 * Description:
 */
class BottomNavFragment : Fragment() {

    private var bottomNavItemVisibleCount = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_bottom_nav, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Change Bottom Nav style
        bottom_nav_chip_group.setOnCheckedChangeListener { group, _ ->
            when (group.checkedChipId) {
                R.id.auto_chip -> {
                    bottom_nav.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_AUTO
                }
                R.id.selected_chip -> {
                    bottom_nav.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_SELECTED
                }
                R.id.labeled_chip -> {
                    bottom_nav.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
                }
                R.id.unlabeled_chip -> {
                    bottom_nav.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED
                }
            }
        }
        // Add a Bottom Nav ComponentMenuItem
        add_item_button.setOnClickListener {
            if (bottomNavItemVisibleCount < MAX_BOTTOM_NAV_ITEM_COUNT) {
                bottom_nav.menu.getItem(bottomNavItemVisibleCount).isVisible = true
                bottomNavItemVisibleCount++
            }
        }
        // Remove a Bottom Nav ComponentMenuItem
        remove_item_button.setOnClickListener {
            if (bottomNavItemVisibleCount > 0) {
                bottom_nav.menu.getItem(bottomNavItemVisibleCount - 1).isVisible = false
                bottomNavItemVisibleCount--
            }
        }
    }

    companion object {

        private const val MAX_BOTTOM_NAV_ITEM_COUNT = 5
    }
}
