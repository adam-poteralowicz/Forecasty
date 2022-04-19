package com.apap.forecasty.data.network

import com.apap.forecasty.data.GeolocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeolocationService {

    @GET("geo/1.0/direct")
    suspend fun getGeolocationForCity(
        @Query("q") city: String,
        @Query("appid") appId: String,
    ): List<GeolocationResponse>

}