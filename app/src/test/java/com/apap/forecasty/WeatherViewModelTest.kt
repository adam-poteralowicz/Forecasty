package com.apap.forecasty

import com.apap.forecasty.presentation.viewModel.WeatherViewModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class WeatherViewModelTest {

    private lateinit var subject: WeatherViewModel
    private val dayTime = 10

    @Before
    fun setUp() {
        initViewModel()
    }

    private fun initViewModel() {
        subject = WeatherViewModel()
    }

    @Test
    fun `should retrieve cloudy drawable for forecast with clouds`() {
        val result = subject.getImageForWeather("Clouds", dayTime)

        assertThat(result).isEqualTo(R.drawable.ic_cloudy)
    }

    @Test
    fun `should retrieve day drawable for forecast with clear sky during daytime`() {
        val result = subject.getImageForWeather("Clear", dayTime)

        assertThat(result).isEqualTo(R.drawable.ic_day)
    }

    @Test
    fun `should retrieve night drawable for forecast with clear sky during nighttime`() {
        val result = subject.getImageForWeather("Clear", 23)

        assertThat(result).isEqualTo(R.drawable.ic_night)
    }

    @Test
    fun `should retrieve snowy drawable for forecast with snow`() {
        val result = subject.getImageForWeather("Snow", dayTime)

        assertThat(result).isEqualTo(R.drawable.ic_snowy)
    }

    @Test
    fun `should retrieve rainy drawable for forecast with rain`() {
        val result = subject.getImageForWeather("Rain", dayTime)

        assertThat(result).isEqualTo(R.drawable.ic_rainy)
    }
}