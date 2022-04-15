package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Minutely(
    val dt: Int,
    val precipitation: Double,
) : Parcelable
