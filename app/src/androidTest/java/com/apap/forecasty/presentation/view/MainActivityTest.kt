package com.apap.forecasty.presentation.view

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import org.junit.After
import org.junit.Before
import org.junit.Rule

class MainActivityTest {

    @get:Rule val composeTestRule = createComposeRule()

    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        Intents.init()
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        Intents.release()
        activityScenario.close()
    }
}