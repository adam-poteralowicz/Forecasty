package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temperature(
    val day: Float,
    val min: Float,
    val max: Float,
    val night: Float,
    val evening: Float,
    val morning: Float,
) : Parcelable