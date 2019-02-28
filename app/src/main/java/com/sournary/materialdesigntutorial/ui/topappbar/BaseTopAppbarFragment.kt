package com.sournary.materialdesigntutorial.ui.topappbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.extension.showSnackbar

/**
 * Created on 2019-09-22 by Sang
 * Description:
 **/
abstract class BaseTopAppbarFragment : Fragment() {

    private val viewModel: TopAppbarViewModel by activityViewModels()

    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_top_appbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.topAppbarDestItemPosition = 0
            navController.popBackStack()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                viewModel.topAppbarDestItemPosition = 0
                navController.popBackStack()
            }
            R.id.top_appbar_type_action -> showTopAppbarTypeDialog()
            R.id.choose_theme_action -> showSnackbar("Setting theme")
            else -> showSnackbar("Unknown action")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showTopAppbarTypeDialog() {
        var choicePosition = -1
        val choiceOptions = listOf(
            "Regular TopAppbar",
            "Scrollable TopAppbar",
            "Scrollable TopAppbar with transparent status bar",
            "collapsing TopAppbar",
            "Toolbar"
        ).toTypedArray()
        MaterialAlertDialogBuilder(context ?: return)
            .setTitle("Choose top appbar type")
            .setPositiveButton(getString(R.string.positive)) { _, _ ->
                if (viewModel.topAppbarDestItemPosition != choicePosition && choicePosition != -1) {
                    when (choicePosition) {
                        0 -> navigateToTopAppbarDest(R.id.top_appbar_dest)
                        1 -> navigateToTopAppbarDest(R.id.scrollable_top_appbar_dest)
                        2 -> navigateToTopAppbarDest(R.id.scrollable_top_appbar_transparent_status_bar_dest)
                        3 -> navigateToTopAppbarDest(R.id.collapsing_top_appbar_dest)
                        4 -> navigateToTopAppbarDest(R.id.toolbar_dest)
                        else -> showSnackbar("Unknown dest")
                    }
                    viewModel.topAppbarDestItemPosition = choicePosition
                }
            }
            .setNegativeButton(getString(R.string.negative), null)
            .setSingleChoiceItems(choiceOptions, viewModel.topAppbarDestItemPosition) { _, which ->
                choicePosition = which
            }.show()
    }

    private fun navigateToTopAppbarDest(@IdRes destId: Int) {
        val options = navOptions {
            anim {
                enter = R.anim.anim_grow_fade_in_from_bottom
                exit = R.anim.anim_fade_out
                popEnter = R.anim.anim_fade_in
                popExit = R.anim.anim_shrink_fade_out_from_bottom
            }
            popUpTo(getCurrentDestId()) { inclusive = true }
        }
        navController.navigate(destId, null, options)
    }

    @IdRes
    abstract fun getCurrentDestId(): Int
}
