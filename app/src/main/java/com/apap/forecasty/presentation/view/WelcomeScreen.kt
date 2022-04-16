package com.apap.forecasty.presentation.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apap.forecasty.R
import com.apap.forecasty.presentation.viewModel.WelcomeViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
) {

    val state by viewModel.loadingStateFlow.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Toolbar()
        LoadingComponent(
            success = {},
            error = {},
            loadingState = state,
        )
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(PaddingValues(bottom = 30.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Button(onClick = { viewModel.onProceedClicked() }) {
                Text(text = "Proceed", color = Color.White)
            }
        }
    }
}

@Composable
fun Toolbar() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
    )
}