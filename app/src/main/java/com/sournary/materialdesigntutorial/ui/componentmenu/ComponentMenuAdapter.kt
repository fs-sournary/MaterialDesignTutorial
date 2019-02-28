package com.sournary.materialdesigntutorial.ui.componentmenu

import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sournary.materialdesigntutorial.R
import com.sournary.materialdesigntutorial.model.ComponentMenuItem

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
class ComponentMenuAdapter : ListAdapter<ComponentMenuItem, ComponentMenuViewHolder>(MENU_COMPARATOR) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ComponentMenuViewHolder = ComponentMenuViewHolder.create(parent, R.layout.item_menu)

    override fun onBindViewHolder(holder: ComponentMenuViewHolder, position: Int) {
        val listener = object : ComponentMenuItemListener {
            override fun onItemClick(v: View, position: Int, item: ComponentMenuItem) {
                val navController = Navigation.findNavController(v)
                when (position) {
                    0 -> navController.navigate(ComponentMenuFragmentDirections.navigateToBottomAppbar())
                    1 -> navController.navigate(ComponentMenuFragmentDirections.navigateToBottomNav())
                    2 -> navController.navigate(ComponentMenuFragmentDirections.navigateToButton())
                    3 -> navController.navigate(ComponentMenuFragmentDirections.navigateToExpandableCard())
                    4 -> navController.navigate(ComponentMenuFragmentDirections.navigateToChip())
                    5 -> navController.navigate(ComponentMenuFragmentDirections.navigateToFab())
                    6 -> navController.navigate(ComponentMenuFragmentDirections.navigateToFont())
                    8 -> navController.navigate(ComponentMenuFragmentDirections.navigateToTextField())
                    7 -> navController.navigate(ComponentMenuFragmentDirections.navigateToFab())
                    9 -> navController.navigate(ComponentMenuFragmentDirections.navigateToTopAppbar())
                    10 -> navController.navigate(ComponentMenuFragmentDirections.navigateToTransformation())
                    12 -> navController.navigate(ComponentMenuFragmentDirections.navigateToDatePicker())
                    13 -> navController.navigate(ComponentMenuFragmentDirections.navigateToCraneShapeTheme())
                }
            }
        }
        holder.bindView(getItem(position), listener)
    }

    companion object {

        val MENU_COMPARATOR = object : DiffUtil.ItemCallback<ComponentMenuItem>() {
            override fun areItemsTheSame(oldItem: ComponentMenuItem, newItem: ComponentMenuItem): Boolean =
                oldItem.header == newItem.header

            override fun areContentsTheSame(oldItem: ComponentMenuItem, newItem: ComponentMenuItem): Boolean =
                oldItem.subhead == newItem.subhead
        }
    }
}
