package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val latitude: String,
    val longitude: String,
    val timezone: String,
    val timezoneOffset: Int,
    val currentForecast: CurrentForecast,
    val minutelyForecast: List<MinutelyForecast>,
    val hourlyForecast: List<HourlyForecast>,
    val dailyForecast: List<DailyForecast>,
    val weatherAlerts: List<WeatherAlert>,
) : Parcelable
