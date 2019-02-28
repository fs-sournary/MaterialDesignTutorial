package com.sournary.materialdesigntutorial.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.sournary.materialdesigntutorial.model.Cheese
import com.sournary.materialdesigntutorial.source.LoadingListDataSource

/**
 * Created in 14/10/2019 by Sang
 */
class LoadingListRepository {

    fun getLoadingLists(): LiveData<PagedList<Cheese>> = LoadingListDataSource.toLiveData(15)
}
