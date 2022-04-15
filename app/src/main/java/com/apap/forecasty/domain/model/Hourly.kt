package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hourly(
    val dt: Int,
    val temperature: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Double,
    val uvIndex: Double,
    val clouds: Int,
    val visibility: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val windGust: Double,
    val weather: Weather,
    val precipitationProbability: Int
) : Parcelable
