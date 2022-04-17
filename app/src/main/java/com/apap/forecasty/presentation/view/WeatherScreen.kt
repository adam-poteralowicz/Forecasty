package com.apap.forecasty.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apap.forecasty.R
import com.apap.forecasty.domain.model.Forecast
import com.apap.forecasty.ui.theme.ForecastyBlue
import java.time.LocalDateTime

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherScreen (forecast: Forecast?) {
    val isLightTheme = isSystemInDarkTheme().not()

    Surface(Modifier.fillMaxSize()) {
        CurrentWeather(forecast, isLightTheme)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeather(
    forecast: Forecast?,
    isLightTheme: Boolean,
) {
    forecast ?: return
    val currentForecast = forecast.currentForecast
    val currentHour = LocalDateTime.now().hour
    val isNight: Boolean = currentHour < 6 || currentHour > 18

    Box(modifier = Modifier
        .background(if (isLightTheme) ForecastyBlue else Color.Black)
        .wrapContentSize(align = Alignment.Center)
        .padding(4.dp)) {
        Column(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WeatherImage(
                modifier = Modifier.scale(5f),
                image = when (currentForecast.weather[0].main) {
                    "Clear" -> if (isNight) R.drawable.ic_night else R.drawable.ic_day
                    "Clouds" -> R.drawable.ic_cloudy
                    "Rain" -> R.drawable.ic_rainy
                    "Snow" -> R.drawable.ic_snowy
                    else -> R.drawable.ic_day
                },
                contentDescription = "weather_image",
            )
        }
    }
}

@Composable
fun WeatherImage(
    modifier: Modifier = Modifier,
    image: Int,
    contentDescription: String,
) {
    val paintImage: Painter = painterResource(id = image)
    Image(
        modifier = modifier,
        painter = paintImage,
        contentDescription = contentDescription,
    )
}
