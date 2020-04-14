package com.dorokhov.openweatherapp.ui.main.weather_main.viewmodel

import com.dorokhov.openweatherapp.model.WeatherModel

fun WeatherViewModel.setCoord(coord: String) {
    val update = getCurrentNewStateOrNew()
    update.inputCoordsFields.coordinates = coord
    setViewState(update)
}

fun WeatherViewModel.setWeatherListData(list: List<WeatherModel>) {
    val update = getCurrentNewStateOrNew()
    update.weatherFields.weatherList = list as ArrayList<WeatherModel>
    setViewState(update)
}

fun WeatherViewModel.removeWeatherItem(weatherItem: WeatherModel) {
    val update = getCurrentNewStateOrNew()
    val list = update.weatherFields.weatherList.toMutableList()
    for (i in 0..(list.size - 1)) {
        if (list[i] == weatherItem) {
            list.remove(weatherItem)
            break
        }
    }
    setWeatherListData(list)
}