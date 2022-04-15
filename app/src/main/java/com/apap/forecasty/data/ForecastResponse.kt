package com.apap.forecasty.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    @Json(name = "lat") val latitude: String?,
    @Json(name = "lon") val longitude: String?,
    @Json(name = "timezone") val timezone: String?,
    @Json(name = "timezone_offset") val timezoneOffset: Int?,
    @Json(name = "current") val current: CurrentForecast?,
    @Json(name = "minutely") val minutely: List<MinutelyForecast>?,
    @Json(name = "hourly") val hourly: List<HourlyForecast>?,
    @Json(name = "daily") val daily: List<DailyForecast>?,
    @Json(name = "alerts") val alerts: List<WeatherAlert>?,
) {

    @JsonClass(generateAdapter = true)
    data class Weather(
        @Json(name = "id") val id: Int?,
        @Json(name = "main") val main: String?,
        @Json(name = "description") val description: String?,
        @Json(name = "icon") val icon: String?,
    )

    @JsonClass(generateAdapter = true)
    data class CurrentForecast(
        @Json(name = "dt") val dt: Int?,
        @Json(name = "sunrise") val sunrise: Int?,
        @Json(name = "sunset") val sunset: Int?,
        @Json(name = "temp") val temperature: Double?,
        @Json(name = "feels_like") val feelsLike: Double?,
        @Json(name = "pressure") val pressure: Int?,
        @Json(name = "humidity") val humidity: Int?,
        @Json(name = "dew_point") val dewPoint: Double?,
        @Json(name = "uvi") val uvIndex: Double?,
        @Json(name = "clouds") val clouds: Int?,
        @Json(name = "visibility") val visibility: Int?,
        @Json(name = "wind_speed") val windSpeed: Int?,
        @Json(name = "wind_deg") val windDirection: Int?,
        @Json(name = "weather") val weather: Weather?,
        @Json(name = "rain") val rain: Rain?,
    ) {


        @JsonClass(generateAdapter = true)
        data class Rain(
            @Json(name = "1h") val hourlyRainVolume: Double?
        )
    }

    @JsonClass(generateAdapter = true)
    data class MinutelyForecast(
        @Json(name = "dt") val dt: Int?,
        @Json(name = "precipitation") val precipitation: Double?,
    )

    @JsonClass(generateAdapter = true)
    data class HourlyForecast(
        @Json(name = "dt") val dt: Int?,
        @Json(name = "temp") val temperature: Double?,
        @Json(name = "feels_like") val feelsLike: Double?,
        @Json(name = "pressure") val pressure: Int?,
        @Json(name = "humidity") val humidity: Int?,
        @Json(name = "dew_point") val dewPoint: Double?,
        @Json(name = "uvi") val uvIndex: Double?,
        @Json(name = "clouds") val clouds: Int?,
        @Json(name = "visibility") val visibility: Int?,
        @Json(name = "wind_speed") val windSpeed: Double?,
        @Json(name = "wind_deg") val windDirection: Int?,
        @Json(name = "wind_gust") val windGust: Double?,
        @Json(name = "weather") val weather: Weather?,
        @Json(name = "pop") val precipitationProbability: Int?,
    )

    @JsonClass(generateAdapter = true)
    data class DailyForecast(
        @Json(name = "dt") val dt: Int?,
        @Json(name = "sunrise") val sunrise: Int?,
        @Json(name = "sunset") val sunset: Int?,
        @Json(name = "moonrise") val moonrise: Int?,
        @Json(name = "moonset") val moonset: Int?,
        @Json(name = "moon_phase") val moonPhase: Double?,
        @Json(name = "temp") val temperature: Temperature?,
        @Json(name = "feels_like") val feelsLike: FeelsLike?,
        @Json(name = "pressure") val pressure: Int?,
        @Json(name = "humidity") val humidity: Int?,
        @Json(name = "dew_point") val dewPoint: Double?,
        @Json(name = "wind_speed") val windSpeed: Double?,
        @Json(name = "wind_deg") val windDirection: Int?,
        @Json(name = "weather") val weather: Weather?,
        @Json(name = "clouds") val clouds: Int?,
        @Json(name = "pop") val precipitationProbability: Int?,
        @Json(name = "rain") val rain: Double?,
        @Json(name = "uvi") val uvIndex: Double?,
    ) {

        @JsonClass(generateAdapter = true)
        data class Temperature(
            @Json(name = "day") val day: Double?,
            @Json(name = "min") val min: Double?,
            @Json(name = "max") val max: Double?,
            @Json(name = "night") val night: Double?,
            @Json(name = "eve") val evening: Double?,
            @Json(name = "morn") val morning: Double?,
        )

        @JsonClass(generateAdapter = true)
        data class FeelsLike(
            @Json(name = "day") val day: Double?,
            @Json(name = "night") val night: Double?,
            @Json(name = "eve") val evening: Double?,
            @Json(name = "morn") val morning: Double?,
        )
    }

    @JsonClass(generateAdapter = true)
    data class WeatherAlert(
        @Json(name = "sender_name") val senderName: String?,
        @Json(name = "event") val event: String?,
        @Json(name = "start") val start: Int?,
        @Json(name = "end") val end: Int?,
        @Json(name = "description") val description: String?,
    )
}