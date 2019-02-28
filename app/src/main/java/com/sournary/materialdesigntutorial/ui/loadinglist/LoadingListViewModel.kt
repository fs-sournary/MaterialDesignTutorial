package com.sournary.materialdesigntutorial.ui.loadinglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.sournary.materialdesigntutorial.model.Cheese
import com.sournary.materialdesigntutorial.repository.LoadingListRepository

/**
 * Created in 14/10/2019 by Sang
 */
class LoadingListViewModel : ViewModel() {

    private val repo = LoadingListRepository()

    private var source: LiveData<PagedList<Cheese>>? = null
    private val _cheese = MediatorLiveData<PagedList<Cheese>>()
    val cheese: LiveData<PagedList<Cheese>> = _cheese

    init {
        refresh()
    }

    fun refresh() {
        source?.let { _cheese.removeSource(it) }
        val s = repo.getLoadingLists()
        source = s
        _cheese.addSource(s) { _cheese.postValue(it) }
    }
}
