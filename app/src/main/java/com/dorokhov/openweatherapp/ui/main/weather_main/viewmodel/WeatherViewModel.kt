package com.dorokhov.openweatherapp.ui.main.weather_main.viewmodel

import androidx.lifecycle.LiveData
import com.dorokhov.openweatherapp.repository.main.WeatherRepository
import com.dorokhov.openweatherapp.ui.BaseViewModel
import com.dorokhov.openweatherapp.ui.DataState
import com.dorokhov.openweatherapp.ui.main.weather_main.state.WeatherStateEvent
import com.dorokhov.openweatherapp.ui.main.weather_main.state.WeatherViewState
import com.dorokhov.openweatherapp.utils.AbsentLiveData
import javax.inject.Inject

class WeatherViewModel
@Inject
constructor(
    private val weatherRepository: WeatherRepository
): BaseViewModel<WeatherStateEvent, WeatherViewState>() {

    override fun initNewViewState(): WeatherViewState {
        return WeatherViewState()
    }

    override fun handleStateEvent(it: WeatherStateEvent): LiveData<DataState<WeatherViewState>> {
        when (it) {
            is WeatherStateEvent.GetWeatherInCurrentCircle -> {
                return weatherRepository.getWeatherFromCurrentCircle(
                    it.string
                )
            }
            is WeatherStateEvent.DeleteItemInCurrentList -> {
                return AbsentLiveData.create() // вроде не понадобится
            }
            is WeatherStateEvent.None -> {
                return AbsentLiveData.create()
            }

        }
    }

    fun cancelActiveJobs() {
       // blogRepository.cancelActiveJobs()
        handlePendingData()
    }

    private fun handlePendingData() {
        setStateEvent(WeatherStateEvent.None())
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }
}