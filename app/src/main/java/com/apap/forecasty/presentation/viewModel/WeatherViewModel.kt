package com.apap.forecasty.presentation.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.apap.forecasty.R
import com.apap.forecasty.ui.theme.ForecastyBlue
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor() : ViewModel() {

    fun getImageForWeather(conditions: String, time: Int) = when (conditions) {
        "Clear" -> if (isNight(time)) R.drawable.ic_night else R.drawable.ic_day
        "Clouds" -> R.drawable.ic_cloudy
        "Rain" -> R.drawable.ic_rainy
        "Snow" -> R.drawable.ic_snowy
        else -> R.drawable.ic_day
    }

    private fun isNight(time: Int) = time !in (6..18)

    fun getColorForTheme(isLightTheme: Boolean) : Color {
        return if (isLightTheme) Color.White else ForecastyBlue
    }

    fun formatDate(date: LocalDateTime) = date.format(DateTimeFormatter.ISO_DATE).toString()
}