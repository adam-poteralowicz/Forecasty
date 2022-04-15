package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Daily(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val moonrise: Int,
    val moonset: Int,
    val moonPhase: Double,
    val temperature: Temperature,
    val feelsLike: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Double,
    val windSpeed: Double,
    val windDirection: Int,
    val weather: Weather,
    val clouds: Int,
    val precipitationProbability: Int,
    val rain: Double,
    val uvIndex: Double,
) : Parcelable
