package com.apap.forecasty.util

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor

fun Float.round(): String {
    val diff = abs(this - floor(this))
    val roundedValue = when {
        diff <= 0.33 -> floor(this)
        diff <= 0.66 -> floor(this) + 0.5f
        else -> ceil(this)
    }
    return roundedValue.toString()
}