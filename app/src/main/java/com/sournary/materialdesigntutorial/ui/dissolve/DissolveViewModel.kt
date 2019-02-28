package com.sournary.materialdesigntutorial.ui.dissolve

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.sournary.materialdesigntutorial.R

/**
 * 1, Created in 10/7/19 by Sang
 * 2, Description:
 */
class DissolveViewModel : ViewModel() {

    private val position = MutableLiveData(0)

    val image = position.map { IMAGES[it % IMAGES.size] }

    fun nextImage() {
        position.value?.let { position.value = it + 1 }
    }

    fun prevImage() {
        position.value?.let { position.value = if (it == 0) IMAGES.size - 1 else it - 1 }
    }

    companion object {

        private val IMAGES = listOf(
            R.drawable.cheese_1,
            R.drawable.cheese_2,
            R.drawable.cheese_3,
            R.drawable.cheese_4,
            R.drawable.cheese_5
        )
    }
}
