package com.apap.forecasty.presentation.view

import android.os.Bundle
import android.view.WindowManager
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

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            ForecastyTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = WELCOME) {
                    composable(WELCOME) {
                        WelcomeScreen(navigateToWeather = { forecast, city ->
                            val bundle = Bundle()
                            bundle.putParcelable("forecast", forecast)
                            city?.let { bundle.putString("city", city) }
                            navController.navigateWithForecast(
                                route = WEATHER,
                                args = bundle,
                            )
                        })
                    }
                    composable(WEATHER) { entry ->
                        WeatherScreen(
                            forecast = entry.arguments?.getParcelable("forecast"),
                            city = entry.arguments?.getString("city")
                        )
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