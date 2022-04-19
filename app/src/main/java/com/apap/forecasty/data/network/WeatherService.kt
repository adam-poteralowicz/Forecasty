package com.apap.forecasty.data.network

import com.apap.forecasty.data.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
private const val DEFAULT_MEASUREMENT_UNITS = "metric"

interface WeatherService {

    @GET("${BASE_URL}/onecall")
    suspend fun getCurrentForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String = DEFAULT_MEASUREMENT_UNITS,
        @Query("appid") appId: String,
    ): ForecastResponse
}