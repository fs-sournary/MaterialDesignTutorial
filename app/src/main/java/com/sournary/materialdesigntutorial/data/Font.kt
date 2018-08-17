package com.sournary.materialdesigntutorial.data

/**
 * Created: 24/08/2018
 * By: Sang
 * Description:
 */
data class Font(
    val name: String,
    val describe: String,
    val demo: String,
    val style: Int = android.R.style.TextAppearance_DeviceDefault
)
