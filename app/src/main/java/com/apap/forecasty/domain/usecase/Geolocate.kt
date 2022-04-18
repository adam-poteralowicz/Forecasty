package com.apap.forecasty.domain.usecase

import com.apap.forecasty.data.repository.GeolocationRepository
import javax.inject.Inject

class Geolocate @Inject constructor(
    private val geolocationRepository: GeolocationRepository,
) {

    suspend operator fun invoke(city: String) = geolocationRepository.getGeolocationForCity(city)
}