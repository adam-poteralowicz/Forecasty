package com.apap.forecasty.data.repository

import com.apap.forecasty.domain.model.Geolocation

interface GeolocationRepository {

    suspend fun getGeolocationForCity(city: String): Result<List<Geolocation>?>
}