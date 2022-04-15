package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MinutelyForecast(
    val dt: Int,
    val precipitation: Double,
) : Parcelable
