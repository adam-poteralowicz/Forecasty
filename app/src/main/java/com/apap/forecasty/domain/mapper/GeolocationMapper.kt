package com.apap.forecasty.domain.mapper

import com.apap.forecasty.data.GeolocationResponse
import com.apap.forecasty.domain.model.Geolocation

fun List<GeolocationResponse>.toGeolocation() = map {
    Geolocation(
        city = it.city ?: throw ApiParseException("city == null"),
        latitude = it.latitude ?: throw ApiParseException("latitude == null"),
        longitude = it.longitude ?: throw ApiParseException("longitude == null"),
        country = it.country ?: throw ApiParseException("country == null"),
    )
}