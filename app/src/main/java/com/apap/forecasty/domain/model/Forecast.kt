package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val latitude: Float,
    val longitude: Float,
    val timezone: String,
    val timezoneOffset: Int,
    val currentForecast: CurrentForecast,
    val minutelyForecast: List<MinutelyForecast> = emptyList(),
    val hourlyForecast: List<HourlyForecast> = emptyList(),
    val dailyForecast: List<DailyForecast> = emptyList(),
    val weatherAlerts: List<WeatherAlert> = emptyList(),
) : Parcelable
