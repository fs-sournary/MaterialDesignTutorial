package com.sournary.materialdesigntutorial.model

import com.sournary.materialdesigntutorial.R

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
data class ComponentMenuItem(val header: String, val subhead: String, val media: Int) {

    companion object {

        val ALL: List<ComponentMenuItem> by lazy {
            val bottomAppBar = ComponentMenuItem(
                header = "Bottom App Bar",
                media = R.drawable.ic_bottomappbar,
                subhead = "This is demo about BottomAppBarFragment"
            )
            val bottomNavigation = ComponentMenuItem(
                header = "Bottom Navigation",
                media = R.drawable.ic_bottomnavigation,
                subhead = "This is demo about BottomNavigation"
            )
            val button = ComponentMenuItem(
                header = "Button",
                media = R.drawable.ic_button,
                subhead = "This is demo about Button"
            )
            val card = ComponentMenuItem(
                header = "Card",
                media = R.drawable.ic_card,
                subhead = "This is demo about Card"
            )
            val chip = ComponentMenuItem(
                header = "Chip",
                media = R.drawable.ic_chips,
                subhead = "This is demo about chip"
            )
            val fab = ComponentMenuItem(
                header = "Floating action Button",
                media = R.drawable.ic_fab,
                subhead = "This is demo about FloatingActionButton"
            )
            val font = ComponentMenuItem(
                header = "Font",
                media = R.drawable.ic_fonts,
                subhead = "This is demo about Font"
            )
            val tab = ComponentMenuItem(
                header = "Tab",
                media = R.drawable.ic_tabs,
                subhead = "This is demo about Tab"
            )
            val textField = ComponentMenuItem(
                header = "Text field",
                media = R.drawable.ic_textfield,
                subhead = "This is demo about TextField"
            )
            val topAppBar = ComponentMenuItem(
                header = "Top App Bar",
                media = R.drawable.ic_topappbar,
                subhead = "This is demo about TopAppBar"
            )
            val transformation = ComponentMenuItem(
                header = "Transformation",
                media = R.drawable.ic_transformation,
                subhead = "This is demo about Transformation"
            )
            val modalBottomSheet = ComponentMenuItem(
                header = "Model Bottom Sheet",
                media = R.drawable.ic_bottomsheet,
                subhead = "This is demo about model bottom sheet"
            )
            val datePicker = ComponentMenuItem(
                header = "Date picker",
                media = R.drawable.ic_dialog,
                subhead = "This is demo about date picker"
            )
            val theme = ComponentMenuItem(
                header = "Theme",
                media = R.drawable.ic_shape,
                subhead = "This is demo about Theme"
            )
            mutableListOf(
                bottomAppBar, bottomNavigation, button, card, chip, fab, font,
                tab, textField, topAppBar, transformation, modalBottomSheet, datePicker, theme
            )
        }
    }
}
