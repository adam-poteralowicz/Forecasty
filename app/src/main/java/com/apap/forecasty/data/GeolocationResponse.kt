package com.apap.forecasty.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeolocationResponse(
    @Json(name = "name") val city: String?,
    @Json(name = "lat") val latitude: Float?,
    @Json(name = "lon") val longitude: Float?,
    @Json(name = "country") val country: String?,
)