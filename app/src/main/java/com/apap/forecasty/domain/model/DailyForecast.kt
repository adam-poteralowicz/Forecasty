package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyForecast(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val moonrise: Int,
    val moonset: Int,
    val moonPhase: Float,
    val temperature: Temperature,
    val feelsLike: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Float,
    val windSpeed: Float,
    val windDirection: Int,
    val weather: List<Weather>,
    val clouds: Int,
    val precipitationProbability: Float,
    val rain: Float?,
    val uvIndex: Float,
) : Parcelable
