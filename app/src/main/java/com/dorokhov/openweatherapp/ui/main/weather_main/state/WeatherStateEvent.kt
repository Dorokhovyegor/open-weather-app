package com.dorokhov.openweatherapp.ui.main.weather_main.state

sealed class WeatherStateEvent{

    data class GetWeatherInCurrentCircle(
        var string: String
    ): WeatherStateEvent()

    class DeleteItemInCurrentList: WeatherStateEvent()

    class None: WeatherStateEvent()

}