package com.dorokhov.openweatherapp.ui.main.weather_main.state

import com.dorokhov.openweatherapp.api.responses.SingleItemWeather
import com.dorokhov.openweatherapp.model.WeatherModel
import com.dorokhov.openweatherapp.ui.main.weather_main.state.WeatherViewState.InputCoordFields.InputCoordinatesError.Companion.mustBeValid
import com.dorokhov.openweatherapp.ui.main.weather_main.state.WeatherViewState.InputCoordFields.InputCoordinatesError.Companion.mustFillField
import com.dorokhov.openweatherapp.ui.main.weather_main.state.WeatherViewState.InputCoordFields.InputCoordinatesError.Companion.none

data class WeatherViewState(
    var weatherFields: WeatherFields = WeatherFields(),
    var inputCoordsFields: InputCoordFields = InputCoordFields()
) {

    data class WeatherFields(
        var weatherList: ArrayList<WeatherModel> = ArrayList()
    )

    data class InputCoordFields(
        var coordinates: String? = null
    ) {

        class InputCoordinatesError {
            companion object {
                fun mustFillField(): String {
                    return "Поле с координатами должно быть заполнено"
                }

                fun mustBeValid(): String {
                    return "Введите координаты правильно (да, сложно, но я не знаю, кто придумал их вводить вручную)"
                }

                fun none(): String {
                    return "none"
                }
            }
        }

        fun isValidForInputCoordinates(): String {
            val regex = Regex("^(-?\\d+(\\.\\d+)?),\\s*(-?\\d+(\\.\\d+)?)\$")

            if (coordinates.isNullOrBlank()) {
                return mustFillField()
            }

            if (!coordinates!!.trim().matches(regex)) {
                return mustBeValid()
            }

            return none()
        }

    }
}
