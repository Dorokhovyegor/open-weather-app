package com.dorokhov.openweatherapp.di.main

import androidx.lifecycle.ViewModel
import com.dorokhov.openweatherapp.di.ViewModelKey
import com.dorokhov.openweatherapp.ui.main.weather_main.viewmodel.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindWeatherViewModel(weatherViewModel: WeatherViewModel): ViewModel

}