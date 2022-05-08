package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geolocation(
    val city: String,
    val latitude: Float,
    val longitude: Float,
    val country: String,
): Parcelable

fun List<Geolocation>?.isNotNullOrEmpty(): Boolean = this != null && isNotEmpty()