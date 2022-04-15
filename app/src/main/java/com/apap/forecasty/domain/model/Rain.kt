package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(
    val hourlyRainVolume: Double
) : Parcelable
