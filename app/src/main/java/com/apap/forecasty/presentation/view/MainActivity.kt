package com.apap.forecasty.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apap.forecasty.ui.theme.ForecastyTheme
import com.apap.forecasty.util.navigateWithForecast
import dagger.hilt.android.AndroidEntryPoint

private const val WELCOME = "welcome"
private const val WEATHER = "weather/{forecast}"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ForecastyTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = WELCOME) {
                    composable(WELCOME) {
                        WelcomeScreen(navigateToWeather = { forecast ->
                            val bundle = Bundle()
                            bundle.putParcelable("forecast", forecast)
                            navController.navigateWithForecast(
                                route = WEATHER,
                                args = bundle,
                            )
                        })
                    }
                    composable(WEATHER) { entry ->
                        WeatherScreen(entry.arguments?.getParcelable("forecast"))
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ForecastyTheme {

    }
}