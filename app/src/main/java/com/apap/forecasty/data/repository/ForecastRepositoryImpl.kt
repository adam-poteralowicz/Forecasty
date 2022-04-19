package com.apap.forecasty.data.repository

import com.apap.forecasty.BuildConfig
import com.apap.forecasty.data.network.WeatherService
import com.apap.forecasty.domain.mapper.toForecast
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val service: WeatherService,
) : ForecastRepository {

    // TODO: Retrieve forecast for input location
    override suspend fun getForecast(
        latitude: Float,
        longitude: Float,
    ) = Result.runCatching {
        // TODO: Retrieve current location
        service.getCurrentForecast(
            latitude = latitude.toString(),
            longitude = longitude.toString(),
            appId = BuildConfig.WEATHER_API_KEY,
        ).toForecast()
    }
}