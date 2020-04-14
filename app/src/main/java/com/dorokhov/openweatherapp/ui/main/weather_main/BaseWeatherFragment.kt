package com.dorokhov.openweatherapp.ui.main.weather_main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.dorokhov.openweatherapp.ui.DataStateChangeListener
import com.dorokhov.openweatherapp.ui.main.weather_main.viewmodel.WeatherViewModel
import com.dorokhov.openweatherapp.utils.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.Exception

abstract class BaseWeatherFragment : DaggerFragment() {

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var weatherViewModel: WeatherViewModel

    lateinit var stateChangeListener: DataStateChangeListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel = activity?.run {
            ViewModelProvider(this,providerFactory).get(WeatherViewModel::class.java)
        }?: throw Exception("Invalid activity")
        cancelActiveJobs()
    }

    fun cancelActiveJobs() {
        weatherViewModel.cancelActiveJobs()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            stateChangeListener = context as DataStateChangeListener
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }

    }
}