package com.apap.forecasty.data.repository

import com.apap.forecasty.domain.model.Geolocation
import javax.inject.Singleton

@Singleton
class GeolocationCache {
    private lateinit var value: Geolocation

    fun retrieve() : Geolocation? = if (this::value.isInitialized) value else null
    fun save(geolocation: Geolocation) {
        value = geolocation
    }
}