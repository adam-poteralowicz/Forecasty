package com.apap.forecasty.domain.usecase

import com.apap.forecasty.data.repository.ForecastRepository
import com.apap.forecasty.domain.model.Forecast
import javax.inject.Inject

class GetForecast @Inject constructor(
    private val forecastRepository: ForecastRepository,
) {

    suspend operator fun invoke(): Forecast = forecastRepository.getForecast().getOrThrow()
}