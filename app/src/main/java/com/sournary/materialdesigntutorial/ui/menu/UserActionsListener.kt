package com.sournary.materialdesigntutorial.ui.menu

import android.view.View
import com.sournary.materialdesigntutorial.data.Item

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
interface UserActionsListener {

    fun onItemClick(v: View, position: Int, item: Item)
}
