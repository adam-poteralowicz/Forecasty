package com.apap.forecasty.data.repository

import com.apap.forecasty.BuildConfig
import com.apap.forecasty.data.network.GeolocationService
import com.apap.forecasty.domain.mapper.toGeolocation
import com.apap.forecasty.domain.model.Geolocation
import javax.inject.Inject

class GeolocationRepositoryImpl @Inject constructor(
    private val cache: GeolocationCache,
    private val service: GeolocationService
): GeolocationRepository {

    override suspend fun getGeolocationForCity(city: String) = Result.runCatching {
        val geolocation = service.getGeolocationForCity(
            city = city,
            appId = BuildConfig.WEATHER_API_KEY
        )
        geolocation.toGeolocation()
    }

    override fun saveLastGeolocation(geolocation: Geolocation) {
        cache.save(geolocation)
    }

    override fun getLastGeolocation() = cache.retrieve()?.let { Result.success(it) }
}