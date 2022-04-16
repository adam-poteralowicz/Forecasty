package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HourlyForecast(
    val dt: Int,
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
    val windGust: Float?,
    val weather: List<Weather>,
    val precipitationProbability: Float
) : Parcelable
