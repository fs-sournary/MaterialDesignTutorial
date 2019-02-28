package com.sournary.materialdesigntutorial.ui.staggerlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sournary.materialdesigntutorial.model.Cheese
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created in 10/10/2019 by Sang
 */
class StaggerListViewModel : ViewModel() {

    private val _cheeses = MutableLiveData<List<Cheese>>()
    val cheeses: LiveData<List<Cheese>> = _cheeses

    init {
        refresh()
    }

    fun empty() {
        _cheeses.value = emptyList()
    }

    fun refresh() {
        viewModelScope.launch {
            delay(300L)
            _cheeses.value = Cheese.ALL
        }
    }
}
