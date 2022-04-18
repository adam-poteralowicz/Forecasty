package com.apap.forecasty.di

import com.apap.forecasty.data.network.GeolocationService
import com.apap.forecasty.data.network.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun weatherService(retrofit: Retrofit): WeatherService = retrofit.create()

    @Provides
    fun geolocationService(retrofit: Retrofit): GeolocationService = retrofit.create()
}