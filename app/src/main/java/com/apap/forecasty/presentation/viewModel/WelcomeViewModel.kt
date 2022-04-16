package com.apap.forecasty.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apap.forecasty.domain.model.Forecast
import com.apap.forecasty.domain.usecase.GetForecast
import com.apap.forecasty.presentation.view.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val getForecast: GetForecast
) : ViewModel() {

    private val _forecast = MutableStateFlow<Forecast?>(null)
    val forecast = _forecast.asStateFlow()

    private val _loadingStateFlow = MutableStateFlow(LoadingState.Idle)
    val loadingStateFlow = _loadingStateFlow.asStateFlow()

    fun onProceedClicked() {
        loadForecast()
    }

    private fun loadForecast() = viewModelScope.launch {

        _loadingStateFlow.value = LoadingState.Pending
        val forecast = getForecast()
        if (forecast == null) {
            _loadingStateFlow.value = LoadingState.Failure
        } else {
            _loadingStateFlow.value = LoadingState.Done
            _forecast.emit(forecast)
        }
    }
}