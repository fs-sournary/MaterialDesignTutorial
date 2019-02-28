package com.sournary.materialdesigntutorial.ui.componentmenu

import android.view.View
import com.sournary.materialdesigntutorial.model.ComponentMenuItem

/**
 * Created: 17/08/2018
 * By: Sang
 * Description:
 */
interface ComponentMenuItemListener {

    fun onItemClick(v: View, position: Int, item: ComponentMenuItem)
}
