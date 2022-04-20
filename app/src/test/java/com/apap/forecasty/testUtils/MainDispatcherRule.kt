package com.apap.forecasty.testUtils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.ExternalResource

class MainDispatcherRule(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Unconfined
) : ExternalResource() {

    override fun before()  {
        Dispatchers.setMain(dispatcher)
    }

    override fun after() {
        Dispatchers.resetMain()
    }
}