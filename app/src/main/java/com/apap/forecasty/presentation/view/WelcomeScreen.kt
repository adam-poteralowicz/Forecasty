package com.apap.forecasty.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apap.forecasty.R
import com.apap.forecasty.domain.model.Forecast
import com.apap.forecasty.presentation.viewModel.WelcomeViewModel
import com.apap.forecasty.ui.theme.ForecastyBlue

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    navigateToWeather: (Forecast, String, String) -> Unit,
) {

    val forecast by viewModel.forecast.collectAsState()
    val geolocation by viewModel.geolocation.collectAsState()
    val state by viewModel.loadingStateFlow.collectAsState()
    val isLightTheme = isSystemInDarkTheme().not()

    val keyboardController = LocalSoftwareKeyboardController.current
    var inputLocation by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isLightTheme) ForecastyBlue else Color.Black)
    ) {
        Toolbar(isLightTheme)
        LoadingComponent(
            success = {
                with(requireNotNull(geolocation)) {
                    if (isNotEmpty()) {
                        viewModel.saveGeolocation(this[0])
                        val city = this[0].city
                        val country = this[0].country
                        LaunchedEffect(Unit) {
                            navigateToWeather(requireNotNull(forecast), city, country)
                        }
                    }
                }
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
            LocationChoiceTextField(
                isLightTheme = isLightTheme,
                value = inputLocation,
                onValueChange = {
                    inputLocation = it
                },
                onDone = {
                    keyboardController?.hide()
                    viewModel.onLocationChosen(inputLocation)
                }
            )
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
fun LocationChoiceTextField(
    viewModel: WelcomeViewModel = hiltViewModel(),
    isLightTheme: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    onDone: KeyboardActionScope.() -> Unit,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.padding(bottom = 30.dp),
        label = { LocationChoiceText(viewModel, isLightTheme) },
        singleLine = true,
        colors = viewModel.setOutlinedTextFieldColors(isLightTheme),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = onDone,
        ),
    )
}

@Composable
fun LocationChoiceText(
    viewModel: WelcomeViewModel = hiltViewModel(),
    isLightTheme: Boolean,
) {

    Text(
        text = stringResource(R.string.choose_location),
        color = viewModel.getColorForTheme(isLightTheme),
    )
}