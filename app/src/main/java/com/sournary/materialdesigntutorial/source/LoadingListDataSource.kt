package com.sournary.materialdesigntutorial.source

import android.os.SystemClock
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.sournary.materialdesigntutorial.model.Cheese

/**
 * Created in 14/10/2019 by Sang
 */
class LoadingListDataSource : PositionalDataSource<Cheese>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Cheese>) {
        SystemClock.sleep(3000L)
        callback.onResult(
            Cheese.ALL.subList(
                params.requestedStartPosition,
                params.requestedStartPosition + params.requestedLoadSize
            ),
            params.requestedStartPosition,
            Cheese.ALL.size
        )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Cheese>) {
        SystemClock.sleep(3000L)
        callback.onResult(
            Cheese.ALL.subList(params.startPosition, params.startPosition + params.loadSize)
        )
    }

    companion object Factory : DataSource.Factory<Int, Cheese>() {

        override fun create(): DataSource<Int, Cheese> = LoadingListDataSource()
    }
}
