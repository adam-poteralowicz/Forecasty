package com.apap.forecasty.domain.usecase

import com.apap.forecasty.data.repository.GeolocationRepository
import com.apap.forecasty.domain.model.Geolocation
import javax.inject.Inject

class Geolocate @Inject constructor(
    private val geolocationRepository: GeolocationRepository,
) {

    suspend operator fun invoke(city: String): List<Geolocation>? =
        geolocationRepository.getGeolocationForCity(city).getOrNull()
}