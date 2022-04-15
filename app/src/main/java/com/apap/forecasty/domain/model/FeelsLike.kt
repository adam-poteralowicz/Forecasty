package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeelsLike(
    val day: Double,
    val night: Double,
    val evening: Double,
    val morning: Double,
) : Parcelable
