package com.sournary.materialdesigntutorial.ui.componentmenu

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.ComponentMenuItem
import kotlinx.android.synthetic.main.fragment_component_menu.*

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
class ComponentMenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_component_menu, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMenuItems()
    }

    private fun setupMenuItems() {
        menuRecyclerView.adapter = ComponentMenuAdapter().apply { submitList(ComponentMenuItem.ALL) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_option, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
