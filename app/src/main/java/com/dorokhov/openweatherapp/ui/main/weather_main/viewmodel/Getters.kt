package com.dorokhov.openweatherapp.ui.main.weather_main.viewmodel

import com.dorokhov.openweatherapp.api.responses.Coord

fun WeatherViewModel.getCoord(): String? {
    getCurrentNewStateOrNew().let {
        return it.inputCoordsFields.coordinates
    }
}