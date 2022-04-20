package com.apap.forecasty.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apap.forecasty.domain.usecase.Geolocate
import com.apap.forecasty.domain.usecase.GetForecast
import com.apap.forecasty.testUtils.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WelcomeViewModelTest {

    @get:Rule val mainDispatcherRule = MainDispatcherRule()
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK private lateinit var geolocate: Geolocate
    @MockK private lateinit var getForecast: GetForecast

    private lateinit var subject: WelcomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should geolocate city on location chosen`() {

    }

    @Test
    fun `should emit geolocation data on successful geolocation attempt`() {

    }

    @Test
    fun `should not emit geolocation data on blank location input`() {

    }

    @Test
    fun `should do nothing on proceed clicked when geolocation attempt is unsuccessful`() {

    }

    @Test
    fun `should do nothing on proceed clicked when geolocation data is empty`() {

    }

    @Test
    fun `should load forecast on proceed clicked when geolocation attempt is successful`() {

    }

    @Test
    fun `should emit LoadingState Failure when forecast is not found`() {

    }

    @Test
    fun `should emit LoadingState Success and should emit forecast when forecast is found`() {

    }
}