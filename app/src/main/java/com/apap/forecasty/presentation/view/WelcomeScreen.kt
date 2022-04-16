package com.apap.forecasty.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apap.forecasty.R
import com.apap.forecasty.presentation.viewModel.WelcomeViewModel
import com.apap.forecasty.ui.theme.ForecastyBlue

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
) {

    val forecast by viewModel.forecast.collectAsState()
    val state by viewModel.loadingStateFlow.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ForecastyBlue)
    ) {
        Toolbar()
        LoadingComponent(
            success = {},
            error = {},
            loadingState = state,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(bottom = 30.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Image(
                painterResource(
                    id = R.drawable.ic_weather
                ),
                contentDescription = null,
                Modifier
                    .scale(5f)
                    .padding(PaddingValues(bottom = 85.dp))
            )
            Button(onClick = { viewModel.onProceedClicked() }) {
                Text(text = "Proceed", color = ForecastyBlue, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun Toolbar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        },
    )
}