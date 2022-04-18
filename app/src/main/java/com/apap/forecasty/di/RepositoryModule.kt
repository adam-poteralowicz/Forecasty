package com.apap.forecasty.di

import com.apap.forecasty.data.repository.ForecastRepository
import com.apap.forecasty.data.repository.ForecastRepositoryImpl
import com.apap.forecasty.data.repository.GeolocationRepository
import com.apap.forecasty.data.repository.GeolocationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun forecastRepository(impl: ForecastRepositoryImpl): ForecastRepository

    @Binds
    abstract fun geolocationRepository(impl: GeolocationRepositoryImpl): GeolocationRepository
}