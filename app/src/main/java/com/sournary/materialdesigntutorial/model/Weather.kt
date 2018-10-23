package com.sournary.materialdesigntutorial.model

/**
 * Created: 20/08/2018
 * By: Sang
 * Description:
 */
data class Weather(
    val day: String,
    val avatar: Int,
    var predictCelsius: String,
    val actualCelsius: String
)
