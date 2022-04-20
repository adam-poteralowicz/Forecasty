package com.apap.forecasty.robots

import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions
import com.apap.forecasty.R

inline fun weatherRobot(block: WeatherRobot.() -> Unit) = WeatherRobot.block()

object WeatherRobot {

    fun assertToolbarTitleDisplayed() {
        assertDisplayed(R.string.app_name)
    }

    fun assertLocationTextViewDisplayed(location: String) {
        assertDisplayed(location)
    }

    fun assertDayDrawableDisplayed() {
        assertDisplayed(R.drawable.ic_day)
    }

    fun assertNightDrawableDisplayed() {
        assertDisplayed(R.drawable.ic_night)
    }

    fun assertCloudsDrawableDisplayed() {
        assertDisplayed(R.drawable.ic_cloudy)
    }

    fun assertRainDrawableDisplayed() {
        assertDisplayed(R.drawable.ic_rainy)
    }

    fun assertSnowDrawableDisplayed() {
        assertDisplayed(R.drawable.ic_snowy)
    }

    fun assertTemperatureTextViewDisplayed() {
        assertDisplayed(R.string.celsius_degrees)
    }

    fun clickOnBackButton() {
        BaristaClickInteractions.clickBack()
    }
}