package com.apap.forecasty.presentation.view

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.apap.forecasty.R
import com.apap.forecasty.ui.theme.ForecastyBlue

@Composable
fun Toolbar(isLightTheme: Boolean) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = if (isLightTheme) Color.White else ForecastyBlue,
                fontWeight = FontWeight.ExtraBold
            )
        },
    )
}