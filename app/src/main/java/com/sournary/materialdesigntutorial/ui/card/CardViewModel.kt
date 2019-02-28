package com.sournary.materialdesigntutorial.ui.card

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

/**
 * Created in 9/23/19 by Sang
 * Description:
 */
class CardViewModel : ViewModel() {

    var cardDestItemPosition = 0

    private val _dragItems = MutableLiveData(mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    val dragItems = _dragItems.map { it.toList() }

    fun moveItem(from: Int, to: Int){
        _dragItems.value?.let { list ->
            val item = list.removeAt(from)
            list.add(to, item)
            _dragItems.value = list
        }
    }
}
