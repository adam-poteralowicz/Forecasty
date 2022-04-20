package com.apap.forecasty.testUtils

import com.apap.forecasty.domain.model.CurrentForecast
import com.apap.forecasty.domain.model.Forecast
import com.apap.forecasty.domain.model.Geolocation
import com.apap.forecasty.domain.model.Weather
import io.mockk.mockk
import java.util.*

internal object RandomUtils {

    private fun randomFloat() = Random().nextFloat()
    private fun randomInt() = Random().nextInt()
    private fun randomString() = UUID.randomUUID().toString()

    fun randomGeolocation(city: String = "London") = Geolocation(
        city = city,
        latitude = randomFloat(),
        longitude = randomFloat(),
        country = randomString(),
    )

    fun randomForecast() = Forecast(
        latitude = randomFloat(),
        longitude = randomFloat(),
        timezone = randomString(),
        timezoneOffset = randomInt(),
        currentForecast = randomCurrentForecast(),
        minutelyForecast = emptyList(),
        hourlyForecast = emptyList(),
        dailyForecast = emptyList(),
        weatherAlerts = emptyList(),
    )

    private fun randomCurrentForecast() = CurrentForecast(
        dt = randomInt(),
        sunrise = randomInt(),
        sunset = randomInt(),
        temperature = randomFloat(),
        pressure = randomInt(),
        humidity = randomInt(),
        feelsLike = randomFloat(),
        dewPoint = randomFloat(),
        uvIndex = randomFloat(),
        clouds = randomInt(),
        visibility = randomInt(),
        windSpeed = randomFloat(),
        windDirection = randomInt(),
        weather = listOf(randomWeather()),
        rain = mockk(relaxed = true),
    )

    private fun randomWeather() = Weather(
        id = randomInt(),
        main = randomString(),
        description = randomString(),
        icon = randomString(),
    )
}