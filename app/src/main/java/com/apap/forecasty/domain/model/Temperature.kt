package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val evening: Double,
    val morning: Double,
) : Parcelable