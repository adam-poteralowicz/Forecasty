package com.apap.forecasty.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    @Json(name = "lat") val latitude: Float?,
    @Json(name = "lon") val longitude: Float?,
    @Json(name = "timezone") val timezone: String?,
    @Json(name = "timezone_offset") val timezoneOffset: Int?,
    @Json(name = "current") val current: Current?,
    @Json(name = "minutely") val minutely: List<Minutely>?,
    @Json(name = "hourly") val hourly: List<Hourly>?,
    @Json(name = "daily") val daily: List<Daily>?,
    @Json(name = "alerts") val alerts: List<Alert>?,
) {

    @JsonClass(generateAdapter = true)
    data class Weather(
        @Json(name = "id") val id: Int?,
        @Json(name = "main") val main: String?,
        @Json(name = "description") val description: String?,
        @Json(name = "icon") val icon: String?,
    )

    @JsonClass(generateAdapter = true)
    data class Current(
        @Json(name = "dt") val dt: Int?,
        @Json(name = "sunrise") val sunrise: Int?,
        @Json(name = "sunset") val sunset: Int?,
        @Json(name = "temp") val temperature: Float?,
        @Json(name = "feels_like") val feelsLike: Float?,
        @Json(name = "pressure") val pressure: Int?,
        @Json(name = "humidity") val humidity: Int?,
        @Json(name = "dew_point") val dewPoint: Float?,
        @Json(name = "uvi") val uvIndex: Float?,
        @Json(name = "clouds") val clouds: Int?,
        @Json(name = "visibility") val visibility: Int?,
        @Json(name = "wind_speed") val windSpeed: Float?,
        @Json(name = "wind_deg") val windDirection: Int?,
        @Json(name = "weather") val weather: List<Weather>?,
        @Json(name = "rain") val rain: Rain?,
    ) {


        @JsonClass(generateAdapter = true)
        data class Rain(
            @Json(name = "1h") val hourlyRainVolume: Float?
        )
    }

    @JsonClass(generateAdapter = true)
    data class Minutely(
        @Json(name = "dt") val dt: Int?,
        @Json(name = "precipitation") val precipitation: Float?,
    )

    @JsonClass(generateAdapter = true)
    data class Hourly(
        @Json(name = "dt") val dt: Int?,
        @Json(name = "temp") val temperature: Float?,
        @Json(name = "feels_like") val feelsLike: Float?,
        @Json(name = "pressure") val pressure: Int?,
        @Json(name = "humidity") val humidity: Int?,
        @Json(name = "dew_point") val dewPoint: Float?,
        @Json(name = "uvi") val uvIndex: Float?,
        @Json(name = "clouds") val clouds: Int?,
        @Json(name = "visibility") val visibility: Int?,
        @Json(name = "wind_speed") val windSpeed: Float?,
        @Json(name = "wind_deg") val windDirection: Int?,
        @Json(name = "wind_gust") val windGust: Float?,
        @Json(name = "weather") val weather: List<Weather>?,
        @Json(name = "pop") val precipitationProbability: Float?,
    )

    @JsonClass(generateAdapter = true)
    data class Daily(
        @Json(name = "dt") val dt: Int?,
        @Json(name = "sunrise") val sunrise: Int?,
        @Json(name = "sunset") val sunset: Int?,
        @Json(name = "moonrise") val moonrise: Int?,
        @Json(name = "moonset") val moonset: Int?,
        @Json(name = "moon_phase") val moonPhase: Float?,
        @Json(name = "temp") val temperature: Temperature?,
        @Json(name = "feels_like") val feelsLike: FeelsLike?,
        @Json(name = "pressure") val pressure: Int?,
        @Json(name = "humidity") val humidity: Int?,
        @Json(name = "dew_point") val dewPoint: Float?,
        @Json(name = "wind_speed") val windSpeed: Float?,
        @Json(name = "wind_deg") val windDirection: Int?,
        @Json(name = "weather") val weather: List<Weather>?,
        @Json(name = "clouds") val clouds: Int?,
        @Json(name = "pop") val precipitationProbability: Float?,
        @Json(name = "rain") val rain: Float?,
        @Json(name = "uvi") val uvIndex: Float?,
    ) {

        @JsonClass(generateAdapter = true)
        data class Temperature(
            @Json(name = "day") val day: Float?,
            @Json(name = "min") val min: Float?,
            @Json(name = "max") val max: Float?,
            @Json(name = "night") val night: Float?,
            @Json(name = "eve") val evening: Float?,
            @Json(name = "morn") val morning: Float?,
        )

        @JsonClass(generateAdapter = true)
        data class FeelsLike(
            @Json(name = "day") val day: Float?,
            @Json(name = "night") val night: Float?,
            @Json(name = "eve") val evening: Float?,
            @Json(name = "morn") val morning: Float?,
        )
    }

    @JsonClass(generateAdapter = true)
    data class Alert(
        @Json(name = "sender_name") val senderName: String?,
        @Json(name = "event") val event: String?,
        @Json(name = "start") val start: Int?,
        @Json(name = "end") val end: Int?,
        @Json(name = "description") val description: String?,
    )
}