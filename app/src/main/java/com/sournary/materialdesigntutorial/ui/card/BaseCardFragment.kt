package com.sournary.materialdesigntutorial.ui.card

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.extension.navigateToDestAndClearCurrent
import com.sournary.materialdesigntutorial.extension.showSnackbar

/**
 * Created in 9/23/19 by Sang
 * Description:
 */
abstract class BaseCardFragment : Fragment() {

    private val viewModel: CardViewModel by activityViewModels()

    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.cardDestItemPosition = 0
            navController.popBackStack()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_card, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                viewModel.cardDestItemPosition = 0
                navController.popBackStack()
            }
            R.id.choose_card_type_action -> showTopAppbarTypeDialog()
            R.id.setting_dest -> showSnackbar("Setting dest")
            else -> showSnackbar("Unknown dest")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showTopAppbarTypeDialog() {
        var selectedPosition = -1
        val choices = listOf(
            "Expandable card", "Draggable card", "Drag and Swipe card list", "Swipe dismiss"
        ).toTypedArray()
        MaterialAlertDialogBuilder(context ?: return)
            .setTitle("Choose card type")
            .setSingleChoiceItems(choices, viewModel.cardDestItemPosition) { _, which ->
                selectedPosition = which
            }
            .setPositiveButton(getString(R.string.positive)) { _, _ ->
                if (viewModel.cardDestItemPosition == selectedPosition || selectedPosition == -1) return@setPositiveButton
                when (selectedPosition) {
                    0 -> navigateToDestAndClearCurrent(getCurrentDestId(), R.id.expandable_card_dest)
                    1 -> navigateToDestAndClearCurrent(getCurrentDestId(), R.id.draggable_card_dest)
                    2 -> navigateToDestAndClearCurrent(getCurrentDestId(), R.id.drag_card_dest)
                    3 -> navigateToDestAndClearCurrent(getCurrentDestId(), R.id.swipe_dismiss_dest)
                    else -> showSnackbar("Unknown destination")
                }
                viewModel.cardDestItemPosition = selectedPosition
            }
            .setNegativeButton(getString(R.string.negative), null)
            .show()
    }

    @IdRes
    abstract fun getCurrentDestId(): Int
}
