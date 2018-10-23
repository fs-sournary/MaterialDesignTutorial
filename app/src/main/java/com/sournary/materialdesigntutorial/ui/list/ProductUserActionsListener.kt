package com.sournary.materialdesigntutorial.ui.list

import android.view.View
import com.sournary.materialdesigntutorial.model.Product

/**
 * Created: 11/09/2018
 * By: Sang
 * Description:
 */
interface ProductUserActionsListener {

    fun onItemClick(view: View, product: Product, position: Int)
}
