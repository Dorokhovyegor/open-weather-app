package com.dorokhov.openweatherapp.di.main

import com.dorokhov.openweatherapp.api.WeatherApiService
import com.dorokhov.openweatherapp.repository.main.WeatherRepository
import com.dorokhov.openweatherapp.utils.NetworkManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): WeatherApiService {
        return retrofitBuilder.build().create(WeatherApiService::class.java)
    }

    @MainScope
    @Provides
    fun provideWeatherRepository(
        weatherApiService: WeatherApiService,
        networkManager: NetworkManager
    ): WeatherRepository {
        return WeatherRepository(networkManager, weatherApiService)
    }


}