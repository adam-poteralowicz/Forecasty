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
import androidx.hilt.navigation.compose.hiltViewModel
import com.apap.forecasty.R
import com.apap.forecasty.domain.model.Forecast
import com.apap.forecasty.presentation.viewModel.WeatherViewModel
import com.apap.forecasty.ui.theme.ForecastyBlue
import com.apap.forecasty.util.round
import java.time.LocalDateTime

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
    viewModel: WeatherViewModel = hiltViewModel()
) {
    forecast ?: return
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
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(5f),
                imageResId = viewModel.getImageForWeather(
                    conditions = forecast.currentConditions,
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
    viewModel: WeatherViewModel = hiltViewModel()
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
            color = viewModel.getColorForTheme(isLightTheme),
        )
    }
}