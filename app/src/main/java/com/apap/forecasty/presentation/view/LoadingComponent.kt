package com.apap.forecasty.presentation.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun LoadingComponent(
    idle: @Composable () -> Unit = {},
    pending: @Composable () -> Unit = { LoadingIndicator() },
    success: @Composable () -> Unit,
    error: @Composable () -> Unit,
    loadingState: LoadingState,
) {
    AnimatedContent(targetState = loadingState) { state ->
        when (state) {
            LoadingState.Done -> success()
            LoadingState.Failure -> error()
            LoadingState.Pending -> pending()
            LoadingState.Idle -> idle()
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(60.dp).align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Positioning satellites...",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(PaddingValues(top = 32.dp)),
            )
        }
    }
}

enum class LoadingState {
    Done, Failure, Idle, Pending
}