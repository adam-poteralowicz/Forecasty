package com.apap.forecasty.data.repository

import com.apap.forecasty.BuildConfig
import com.apap.forecasty.data.network.WeatherService
import com.apap.forecasty.domain.mapper.toForecast
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val service: WeatherService,
) : ForecastRepository {

    // TODO: Retrieve forecast for input location
    override suspend fun getForecast() = Result.runCatching {
        // TODO: Retrieve current location
        service.getCurrentForecast(
            latitude = "55.9",
            longitude = "-3.1",
            appId = BuildConfig.WEATHER_API_KEY,
        ).toForecast()
    }
}