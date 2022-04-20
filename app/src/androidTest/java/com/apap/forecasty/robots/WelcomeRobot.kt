package com.apap.forecasty.robots

import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.apap.forecasty.R

inline fun welcomeRobot(block: WelcomeRobot.() -> Unit) = WelcomeRobot.block()

object WelcomeRobot {

    fun assertToolbarTitleDisplayed() {
        assertDisplayed(R.string.app_name)
    }

    fun assertLocationChoiceTextFieldDisplayed() {
        assertDisplayed(R.string.choose_location)
    }

    fun assertProceedButtonDisplayed() {
        assertDisplayed(R.string.proceed_button_text)
    }

    fun assertWelcomeLogoDisplayed() {
        assertDisplayed(R.drawable.ic_weather)
    }

    fun clickOnLocationChoiceTextField() {
        clickOn(R.string.choose_location)
    }

    fun clickOnProceedButton() {
        clickOn(R.string.proceed_button_text)
    }
}