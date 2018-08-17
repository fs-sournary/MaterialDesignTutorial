package com.sournary.materialdesigntutorial

import androidx.lifecycle.ViewModel

/**
 * Created by fs-sournary.
 * Date: 8/18/18.
 * Description:
 */
class SharedViewModel : ViewModel() {

    val backPressEvent = SingleLiveEvent<Void>()
}
