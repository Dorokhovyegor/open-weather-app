package com.dorokhov.openweatherapp.di

import com.dorokhov.openweatherapp.di.main.MainFragmentBuildersModule
import com.dorokhov.openweatherapp.di.main.MainModule
import com.dorokhov.openweatherapp.di.main.MainScope
import com.dorokhov.openweatherapp.di.main.MainViewModelModule
import com.dorokhov.openweatherapp.ui.main.MainActivity
import com.dorokhov.openweatherapp.ui.main.weather_main.ViewWeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainModule::class, MainFragmentBuildersModule::class, MainViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}