package com.apap.forecasty.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.apap.forecasty.domain.usecase.Geolocate
import com.apap.forecasty.domain.usecase.GetForecast
import com.apap.forecasty.presentation.view.LoadingState
import com.apap.forecasty.testUtils.MainDispatcherRule
import com.apap.forecasty.testUtils.RandomUtils.randomForecast
import com.apap.forecasty.testUtils.RandomUtils.randomGeolocation
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
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

    private fun initViewModel() {
        subject = WelcomeViewModel(geolocate, getForecast)
    }

    @Test
    fun `should geolocate city on location chosen`() = runTest {
        initViewModel()
        subject.onLocationChosen("London")

        coVerify { geolocate("London") }
    }

    @Test
    fun `should emit geolocation data on successful geolocation attempt`() = runTest {
        val city = "London"
        val expectedGeolocation = listOf(randomGeolocation(city))
        coEvery { geolocate(city) } returns expectedGeolocation

        initViewModel()
        subject.onLocationChosen(city)

        subject.geolocation.test {
            assertThat(expectMostRecentItem()).isEqualTo(expectedGeolocation)
        }
    }

    @Test
    fun `should do nothing when geolocation attempt is unsuccessful`() = runTest {
        coEvery { geolocate("") } returns null

        initViewModel()
        subject.onLocationChosen("")

        subject.geolocation.test {
            awaitItem() // skip initial value
            expectNoEvents()
        }
    }

    @Test
    fun `should do nothing when geolocation data is empty`() = runTest {
        initViewModel()

        subject.geolocation.test {
            assertThat(expectMostRecentItem()).isEmpty()
            expectNoEvents()
        }
    }

    @Test
    fun `should load forecast when geolocation attempt is successful`() = runTest {
        val expectedResult = listOf(randomGeolocation())
        coEvery { geolocate("London") } returns expectedResult

        initViewModel()
        subject.onLocationChosen("London")

        subject.geolocation.test {
            assertThat(expectMostRecentItem()).isEqualTo(expectedResult)
            expectNoEvents()
        }
    }

    @Test
    fun `should emit LoadingState Failure when forecast is not found`() = runTest {
        coEvery { getForecast(any(), any()) } returns null

        initViewModel()

        subject.loadingStateFlow.test {
            assertThat(expectMostRecentItem()).isEqualTo(LoadingState.Failure)
        }
    }

    @Test
    fun `should emit LoadingState Done and should emit forecast when forecast is found`() = runTest {
        val expectedResult = randomForecast()
        coEvery { getForecast(any(), any()) } returns expectedResult

        initViewModel()

        subject.loadingStateFlow.test {
            assertThat(expectMostRecentItem()).isEqualTo(LoadingState.Done)
        }
        subject.forecast.test {
            assertThat(expectMostRecentItem()).isEqualTo(expectedResult)
        }
    }
}