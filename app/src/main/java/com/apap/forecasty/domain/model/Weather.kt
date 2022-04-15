package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
) : Parcelable
