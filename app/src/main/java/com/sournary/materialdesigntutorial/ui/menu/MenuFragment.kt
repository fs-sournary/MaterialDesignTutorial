package com.sournary.materialdesigntutorial.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.data.Item
import com.sournary.materialdesigntutorial.ui.BaseFragment

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
class MenuFragment : BaseFragment() {

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_menu, container, false)
        initMenuList()
        return rootView
    }

    private fun initMenuList() {
        menuAdapter = MenuAdapter()
        val menuRecyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_menu)
        menuRecyclerView.adapter = menuAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMenuList()
    }

    private fun setupMenuList() {
        menuAdapter.submitList(getList())
    }

    private fun getList(): List<Item> {
        val bottomAppBar = Item(
            header = "Bottom App Bar",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_bottomappbar,
            supportText = "This is demo about BottomAppBarFragment"
        )
        val bottomNavigation = Item(
            header = "Bottom Navigation",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_bottomnavigation,
            supportText = "This is demo about BottomNavigation"
        )
        val button = Item(
            header = "Button",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_button,
            supportText = "This is demo about Button"
        )
        val card = Item(
            header = "Card",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_card,
            supportText = "This is demo about Card"
        )
        val chip = Item(
            header = "Chip",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_chips,
            supportText = "This is demo about chip"
        )
        val fab = Item(
            header = "Floating action Button",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_fab,
            supportText = "This is demo about FloatingActionButton"
        )
        val font = Item(
            header = "Font",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_fonts,
            supportText = "This is demo about Font"
        )
        val tab = Item(
            header = "Tab",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_tabs,
            supportText = "This is demo about Tab"
        )
        val textField = Item(
            header = "Text field",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_textfield,
            supportText = "This is demo about TextField"
        )
        val topAppBar = Item(
            header = "Top App Bar",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_topappbar,
            supportText = "This is demo about TopAppBar"
        )
        val transformation = Item(
            header = "Transformation",
            subhead = "Material component",
            thumbnail = R.drawable.ic_logo_components_48dp,
            media = R.drawable.ic_transformation,
            supportText = "This is demo about Transformation"
        )

        return mutableListOf(
            bottomAppBar,
            bottomNavigation,
            button,
            card,
            chip,
            fab,
            font,
            tab,
            textField,
            topAppBar,
            transformation
        )
    }

    override fun onBackPress() {
        activity?.finish()
    }
}
