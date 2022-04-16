package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeelsLike(
    val day: Float,
    val night: Float,
    val evening: Float,
    val morning: Float,
) : Parcelable
