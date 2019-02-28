package com.sournary.materialdesigntutorial.ui.iconanimationmenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sournary.materialdesigntutorial.model.IconAnimationItem

/**
 * Created in 24/10/2019 by Sang
 */
class IconAnimationViewModel : ViewModel() {

    private val _items = MutableLiveData<List<IconAnimationItem>>(IconAnimationItem.ALL)
    val items: LiveData<List<IconAnimationItem>> = _items
}
