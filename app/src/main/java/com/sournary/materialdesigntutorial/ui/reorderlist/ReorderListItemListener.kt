package com.sournary.materialdesigntutorial.ui.reorderlist

import com.sournary.materialdesigntutorial.model.Cheese

/**
 * 1, Created in 10/9/19 by Sang
 * 2, Description:
 */
interface ReorderListItemListener {

    fun onLongClick(cheese: Cheese, position: Int, holder: ReorderListViewHolder): Boolean
}
