package com.apap.forecasty.data.repository

import com.apap.forecasty.BuildConfig
import com.apap.forecasty.data.network.GeolocationService
import com.apap.forecasty.domain.mapper.toGeolocation
import javax.inject.Inject

class GeolocationRepositoryImpl @Inject constructor(
    private val service: GeolocationService
): GeolocationRepository {

    override suspend fun getGeolocationForCity(city: String) = Result.runCatching {
        val geolocation = service.getGeolocationForCity(
            city = city,
            appId = BuildConfig.WEATHER_API_KEY
        )
        geolocation.toGeolocation()
    }
}