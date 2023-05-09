package com.apap.forecasty.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apap.forecasty.R
import com.apap.forecasty.domain.model.Forecast
import com.apap.forecasty.presentation.viewModel.WeatherViewModel
import com.apap.forecasty.ui.theme.ForecastyBlue
import com.apap.forecasty.util.round
import java.time.LocalDateTime

@Composable
fun WeatherScreen (forecast: Forecast?, city: String?) {
    val isLightTheme = isSystemInDarkTheme().not()

    Surface(Modifier.fillMaxSize()) {
        forecast?.let {
            CurrentWeather(it, city, isLightTheme)
        }
    }
}

@Composable
fun CurrentWeather(
    forecast: Forecast,
    city: String?,
    isLightTheme: Boolean,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val currentDate = LocalDateTime.now()

    Column(modifier = Modifier
        .background(if (isLightTheme) ForecastyBlue else Color.Black)
        .fillMaxSize()
        .padding(4.dp)
    ) {
        Toolbar(isLightTheme)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            DateAndLocation(
                viewModel.formatDate(currentDate),
                city ?: forecast.timezone,
                isLightTheme
            )
            WeatherImage(
                modifier = Modifier.fillMaxWidth(),
                imageResId = viewModel.getImageForWeather(
                    conditions = viewModel.getWeatherConditions(forecast.currentConditions),
                    time = currentDate.hour,
                )
            )
            TemperatureText(forecast.currentTemperature.round(), isLightTheme)
        }
    }
}

@Composable
fun WeatherImage(
    modifier: Modifier = Modifier,
    imageResId: Int,
) {
    Image(
        modifier = modifier.scale(5f),
        painter = painterResource(id = imageResId),
        contentDescription = "weather_image",
    )
}

@Composable
fun DateAndLocation(
    date: String,
    location: String,
    isLightTheme: Boolean,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .wrapContentSize(align = Alignment.TopStart)
            .padding(16.dp)
    ) {
        Text(
            text = date,
            fontWeight = FontWeight.Medium,
            color = viewModel.getColorForTheme(isLightTheme),
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
                color = viewModel.getColorForTheme(isLightTheme),
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

    Image(
        modifier = modifier,
        painter = painterResource(id = imageResId),
        contentScale = ContentScale.Fit,
        contentDescription = "location_image",
    )
}

@Composable
fun TemperatureText(
    temperature: String,
    isLightTheme: Boolean,
    viewModel: WeatherViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.celsius_degrees, formatArgs = arrayOf(temperature)),
            Modifier.padding(bottom = 75.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 48.sp,
            color = viewModel.getColorForTheme(isLightTheme),
        )
    }
}