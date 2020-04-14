package com.dorokhov.openweatherapp.di.main

import com.dorokhov.openweatherapp.ui.main.weather_main.ViewWeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeWeatherFragment(): ViewWeatherFragment

}