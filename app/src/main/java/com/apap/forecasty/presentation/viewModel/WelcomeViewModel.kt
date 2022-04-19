package com.apap.forecasty.presentation.viewModel

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apap.forecasty.domain.model.Forecast
import com.apap.forecasty.domain.model.Geolocation
import com.apap.forecasty.domain.usecase.Geolocate
import com.apap.forecasty.domain.usecase.GetForecast
import com.apap.forecasty.presentation.view.LoadingState
import com.apap.forecasty.ui.theme.ForecastyBlue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val getForecast: GetForecast,
    private val geolocate: Geolocate,
) : ViewModel() {

    private val _forecast = MutableStateFlow<Forecast?>(null)
    val forecast = _forecast.asStateFlow()

    private val _geolocation = MutableStateFlow<List<Geolocation>?>(emptyList())
    val geolocation = _geolocation.asStateFlow()

    private val _loadingStateFlow = MutableStateFlow(LoadingState.Idle)
    val loadingStateFlow = _loadingStateFlow.asStateFlow()

    fun onProceedClicked(geolocationData: List<Geolocation>?) {
        geolocationData?.let {
            if (it.isNotEmpty()) {
                loadForecast(it[0])
            }
        }
    }

    private fun loadForecast(geolocationData: Geolocation) = viewModelScope.launch {
        _loadingStateFlow.value = LoadingState.Pending
        val forecast = getForecast(geolocationData.latitude, geolocationData.longitude)
        if (forecast == null) {
            _loadingStateFlow.value = LoadingState.Failure
        } else {
            _loadingStateFlow.value = LoadingState.Done
            _forecast.emit(forecast)
        }
    }

    fun onLocationChosen(city: String) = viewModelScope.launch {
        val geolocation = geolocate(city)
        geolocation?.let { _geolocation.emit(geolocation) }
    }

    @Composable
    fun setOutlinedTextFieldColors(isLightTheme: Boolean) =
        TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = getColorForTheme(isLightTheme),
            textColor = getColorForTheme(isLightTheme),
            focusedBorderColor = getColorForTheme(isLightTheme),
            unfocusedBorderColor = getColorForTheme(isLightTheme),
        )

    fun getColorForTheme(isLightTheme: Boolean) : Color {
        return if (isLightTheme) Color.White else ForecastyBlue
    }
}