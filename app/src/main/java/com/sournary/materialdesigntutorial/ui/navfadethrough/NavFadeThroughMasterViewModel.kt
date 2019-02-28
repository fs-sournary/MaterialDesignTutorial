package com.sournary.materialdesigntutorial.ui.navfadethrough

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * Created in 15/10/2019 by Sang
 */
class NavFadeThroughMasterViewModel : ViewModel() {

    var lastSelectedCheese: Cheese? = null
    val expectTransition: Boolean
        get() = lastSelectedCheese != null

    private val _cheese = MutableLiveData<List<Cheese>>(Cheese.ALL)
    val cheese: LiveData<List<Cheese>> = _cheese
}
