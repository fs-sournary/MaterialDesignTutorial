package com.sournary.materialdesigntutorial.ui.navfadethrough

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * Created in 15/10/2019 by Sang
 */
class NavFadeThroughDetailViewModel : ViewModel() {

    private val _cheese = MutableLiveData<Cheese>()
    val cheese: LiveData<Cheese> = _cheese

    fun setCheese(cheese: Cheese?) {
        cheese?.let { _cheese.value = it }
    }
}
