package com.dorokhov.openweatherapp.ui

interface DataStateChangeListener {

    fun onDataStateChange(dataState: DataState<*>?)

}