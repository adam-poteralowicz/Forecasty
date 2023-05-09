package com.apap.forecasty.presentation.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.apap.forecasty.R
import com.apap.forecasty.presentation.viewModel.WeatherConditions.Clear
import com.apap.forecasty.presentation.viewModel.WeatherConditions.Clouds
import com.apap.forecasty.presentation.viewModel.WeatherConditions.NotSupported
import com.apap.forecasty.presentation.viewModel.WeatherConditions.Rain
import com.apap.forecasty.presentation.viewModel.WeatherConditions.Snow
import com.apap.forecasty.ui.theme.ForecastyBlue
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

private const val WEATHER_NOT_SUPPORTED = 0

@HiltViewModel
class WeatherViewModel @Inject constructor() : ViewModel() {

    fun getWeatherConditions(currentConditions: String) = when (currentConditions) {
        "Clear" -> Clear
        "Clouds" -> Clouds
        "Rain" -> Rain
        "Snow" -> Snow
        else -> NotSupported
    }

    fun getImageForWeather(conditions: WeatherConditions, time: Int) = when (conditions) {
        Clear -> if (isNight(time)) R.drawable.ic_night else R.drawable.ic_day
        Clouds -> R.drawable.ic_cloudy
        Rain -> R.drawable.ic_rainy
        Snow -> R.drawable.ic_snowy
        else -> WEATHER_NOT_SUPPORTED
    }

    private fun isNight(time: Int) = time !in (6..18)

    fun getColorForTheme(isLightTheme: Boolean) = if (isLightTheme) Color.White else ForecastyBlue

    fun formatDate(date: LocalDateTime) = date.format(DateTimeFormatter.ISO_DATE).toString()
}

enum class WeatherConditions {
    Clear,
    Clouds,
    Rain,
    Snow,
    NotSupported
}