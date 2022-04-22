package com.apap.forecasty.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apap.forecasty.R
import com.apap.forecasty.domain.model.Forecast
import com.apap.forecasty.domain.model.Geolocation
import com.apap.forecasty.presentation.viewModel.WelcomeViewModel
import com.apap.forecasty.ui.theme.ForecastyBlue

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    navigateToWeather: (Forecast, String) -> Unit,
) {

    val forecast by viewModel.forecast.collectAsState()
    val geolocation by viewModel.geolocation.collectAsState()
    val state by viewModel.loadingStateFlow.collectAsState()
    val isLightTheme = isSystemInDarkTheme().not()

    val keyboardController = LocalSoftwareKeyboardController.current
    var city by rememberSaveable { mutableStateOf("") }
    var isLocationConfirmed by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isLightTheme) ForecastyBlue else Color.Black)
    ) {
        Toolbar(isLightTheme)
        LoadingComponent(
            success = {
                OnForecastSuccessfullyLoaded(forecast, geolocation, navigateToWeather)
                isLocationConfirmed = false
            },
            loadingState = state,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            ForecastyLogo()
            OutlinedTextField(
                value = city,
                onValueChange = {
                    isLocationConfirmed = false
                    city = it
                },
                Modifier.padding(PaddingValues(bottom = 30.dp)),
                label = {
                    Text(
                        text = stringResource(R.string.choose_location),
                        color = viewModel.getColorForTheme(isLightTheme),
                    )
                },
                singleLine = true,
                colors = viewModel.setOutlinedTextFieldColors(isLightTheme),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        isLocationConfirmed = true
                        viewModel.onLocationChosen(city)
                    }
                ),
            )
            Button(
                onClick = { viewModel.onProceedClicked(geolocation) },
                modifier = Modifier
                    .background(
                        color = viewModel.getColorForTheme(isLightTheme),
                        shape = CircleShape
                    )
                    .clickable(
                        enabled = isLocationConfirmed,
                        onClick = {
                            viewModel.onLocationChosen(city)
                            viewModel.onProceedClicked(geolocation)
                        })
            ) {
                Text(
                    text = stringResource(R.string.proceed_button_text),
                    color = if (isLightTheme) ForecastyBlue else Color.Black,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
fun ForecastyLogo() {

    Image(
        painterResource(id = R.drawable.ic_weather),
        contentDescription = null,
        modifier = Modifier
            .scale(5f)
            .padding(bottom = 50.dp)
    )
}

@Composable
private fun OnForecastSuccessfullyLoaded(
    forecast: Forecast?,
    geolocation: List<Geolocation>?,
    navigateToWeather: (Forecast, String) -> Unit,
) {
    forecast?.let { forecast ->
        geolocation?.let { it ->
            if (it.isNotEmpty()) {
                LaunchedEffect(Unit) {
                    navigateToWeather(forecast, "${it[0].city}, ${it[0].country}")
                }
            }
        }
    }
}