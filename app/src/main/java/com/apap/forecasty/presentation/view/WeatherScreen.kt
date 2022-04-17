package com.apap.forecasty.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.apap.forecasty.domain.model.Forecast

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherScreen (forecast: Forecast?) {

    Box(Modifier.fillMaxSize().background(Color.Red)) {

        Text(text = forecast.toString())
    }
}
