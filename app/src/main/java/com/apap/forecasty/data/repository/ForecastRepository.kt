package com.apap.forecasty.data.repository

import com.apap.forecasty.domain.model.Forecast

interface ForecastRepository {

    suspend fun getForecast(latitude: Float, longitude: Float): Result<Forecast?>
}