package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Current(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temperature: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Double,
    val uvIndex: Double,
    val clouds: Int,
    val visibility: Int,
    val windSpeed: Int,
    val windDirection: Int,
    val weather: Weather,
    val rain: Rain,
) : Parcelable
