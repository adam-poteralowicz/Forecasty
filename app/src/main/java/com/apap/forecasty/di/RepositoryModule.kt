package com.apap.forecasty.di

import com.apap.forecasty.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun forecastRepository(impl: ForecastRepositoryImpl): ForecastRepository

    @Binds
    abstract fun geolocationRepository(impl: GeolocationRepositoryImpl): GeolocationRepository

    companion object {

        @Provides
        @Singleton
        fun provideGeolocationCache() = GeolocationCache()
    }
}