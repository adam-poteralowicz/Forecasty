package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentForecast(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temperature: Float,
    val feelsLike: Float,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Float,
    val uvIndex: Float,
    val clouds: Int,
    val visibility: Int,
    val windSpeed: Float,
    val windDirection: Int,
    val weather: List<Weather>,
    val rain: Rain?,
) : Parcelable
