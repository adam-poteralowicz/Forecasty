package com.apap.forecasty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val latitude: String,
    val longitude: String,
    val timezone: String,
    val timezoneOffset: Int,
    val current: Current,
    val minutely: List<Minutely>,
    val hourly: List<Hourly>,
    val daily: List<Daily>,
    val alerts: List<Alert>,
) : Parcelable
