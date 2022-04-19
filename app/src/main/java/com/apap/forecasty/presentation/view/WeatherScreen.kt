package com.apap.forecasty.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.apap.forecasty.R
import com.apap.forecasty.domain.model.Forecast
import com.apap.forecasty.ui.theme.ForecastyBlue
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherScreen (forecast: Forecast?, city: String?) {
    val isLightTheme = isSystemInDarkTheme().not()

    Surface(Modifier.fillMaxSize()) {
        CurrentWeather(forecast, city, isLightTheme)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeather(
    forecast: Forecast?,
    city: String?,
    isLightTheme: Boolean,
) {
    forecast ?: return
    val currentDate = LocalDateTime.now()
    val isNight: Boolean = currentDate.hour < 6 || currentDate.hour > 18

    Column(modifier = Modifier
        .background(if (isLightTheme) ForecastyBlue else Color.Black)
        .fillMaxSize()
        .padding(4.dp)
    ) {
        Toolbar(isLightTheme)
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            DateAndLocation(
                currentDate.format(DateTimeFormatter.ISO_DATE).toString(),
                city ?: forecast.timezone,
                isLightTheme
            )
            WeatherImage(
                modifier = Modifier.fillMaxWidth().scale(5f),
                imageResId = when (forecast.currentConditions) {
                    "Clear" -> if (isNight) R.drawable.ic_night else R.drawable.ic_day
                    "Clouds" -> R.drawable.ic_cloudy
                    "Rain" -> R.drawable.ic_rainy
                    "Snow" -> R.drawable.ic_snowy
                    else -> R.drawable.ic_day
                },
            )
            TemperatureText(forecast.currentTemperature, isLightTheme)
        }
    }
}

@Composable
fun WeatherImage(
    modifier: Modifier = Modifier,
    imageResId: Int,
) {
    val paintImage: Painter = painterResource(id = imageResId)
    Image(
        modifier = modifier,
        painter = paintImage,
        contentDescription = "weather_image",
    )
}

@Composable
fun DateAndLocation(
    date: String,
    location: String,
    isLightTheme: Boolean,
) {
    Column(
        modifier = Modifier
            .wrapContentSize(align = Alignment.TopStart)
            .padding(16.dp)
    ) {
        Text(
            text = date,
            fontWeight = FontWeight.Medium,
            color = if (isLightTheme) Color.White else ForecastyBlue,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Row(modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LocationImage(
                imageResId = R.drawable.ic_location,
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = location,
                fontWeight = FontWeight.Bold,
                color = if (isLightTheme) Color.White else ForecastyBlue,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
fun LocationImage(
    imageResId: Int,
    modifier: Modifier = Modifier,
) {

    val paintImage: Painter = painterResource(id = imageResId)
    Image(
        modifier = modifier,
        painter = paintImage,
        contentScale = ContentScale.Fit,
        contentDescription = "location_image",
    )
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun TemperatureText(
    temperature: String,
    isLightTheme: Boolean,
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "$temperature °C",
            Modifier.padding(PaddingValues(bottom = 75.dp)),
            fontWeight = FontWeight.ExtraBold,
            fontSize = TextUnit(value = 48f, type = TextUnitType.Sp),
            color = if (isLightTheme) Color.White else ForecastyBlue,
        )
    }
}