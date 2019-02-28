package com.sournary.materialdesigntutorial.ui.reorderlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * 1, Created in 10/9/19 by Sang
 * 2, Description:
 */
class ReorderListViewModel : ViewModel() {

    private val _cheeses = MutableLiveData(Cheese.ALL.toMutableList())
    val cheeses = _cheeses.map { it.toList() }

    fun moveCheese(from: Int, to: Int) {
        _cheeses.value?.let { list ->
            val cheese = list.removeAt(from)
            list.add(to, cheese)
            _cheeses.value = list
        }
    }
}
