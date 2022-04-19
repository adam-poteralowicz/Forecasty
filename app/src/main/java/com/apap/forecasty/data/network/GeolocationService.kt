package com.apap.forecasty.data.network

import com.apap.forecasty.data.GeolocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/geo/1.0"

interface GeolocationService {

    @GET("${BASE_URL}/direct")
    suspend fun getGeolocationForCity(
        @Query("q") city: String,
        @Query("appid") appId: String,
    ): List<GeolocationResponse>

}