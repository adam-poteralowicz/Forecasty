package com.apap.forecasty.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apap.forecasty.R
import com.apap.forecasty.data.repository.GeolocationCache
import com.apap.forecasty.domain.model.Geolocation
import com.apap.forecasty.domain.usecase.GetForecast
import com.apap.forecasty.presentation.viewModel.WeatherConditions.Clear
import com.apap.forecasty.presentation.viewModel.WeatherConditions.Clouds
import com.apap.forecasty.presentation.viewModel.WeatherConditions.Rain
import com.apap.forecasty.presentation.viewModel.WeatherConditions.Snow
import com.apap.forecasty.testUtils.MainDispatcherRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK private lateinit var geolocationCache: GeolocationCache
    @MockK private lateinit var getForecast: GetForecast

    private lateinit var subject: WeatherViewModel
    private val dayTime = 10

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun initViewModel() {
        subject = WeatherViewModel(geolocationCache, getForecast)
    }

    @Test
    fun `should retrieve cloudy drawable for forecast with clouds`() {
        initViewModel()
        val result = subject.getImageForWeather(Clouds, dayTime)

        assertThat(result).isEqualTo(R.drawable.ic_cloudy)
    }

    @Test
    fun `should retrieve day drawable for forecast with clear sky during daytime`() {
        initViewModel()
        val result = subject.getImageForWeather(Clear, dayTime)

        assertThat(result).isEqualTo(R.drawable.ic_day)
    }

    @Test
    fun `should retrieve night drawable for forecast with clear sky during nighttime`() {
        initViewModel()
        val result = subject.getImageForWeather(Clear, 23)

        assertThat(result).isEqualTo(R.drawable.ic_night)
    }

    @Test
    fun `should retrieve snowy drawable for forecast with snow`() {
        initViewModel()
        val result = subject.getImageForWeather(Snow, dayTime)

        assertThat(result).isEqualTo(R.drawable.ic_snowy)
    }

    @Test
    fun `should retrieve rainy drawable for forecast with rain`() {
        initViewModel()
        val result = subject.getImageForWeather(Rain, dayTime)

        assertThat(result).isEqualTo(R.drawable.ic_rainy)
    }

    @Test
    fun `should get new forecast based on cached geolocation`() {
        val expectedGeolocation = Geolocation("Wroclaw", 51.0f, 17.0f, "PL")
        every { geolocationCache.retrieve() } returns expectedGeolocation

        initViewModel()
        subject.refreshForecast()

        verify(exactly = 1) { geolocationCache.retrieve() }
        coVerify(exactly = 1) { getForecast(51.0f, 17.0f) }
    }
}