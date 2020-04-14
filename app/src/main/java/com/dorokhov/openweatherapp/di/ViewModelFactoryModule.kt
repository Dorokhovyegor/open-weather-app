package com.dorokhov.openweatherapp.di

import androidx.lifecycle.ViewModelProvider
import com.dorokhov.openweatherapp.utils.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}