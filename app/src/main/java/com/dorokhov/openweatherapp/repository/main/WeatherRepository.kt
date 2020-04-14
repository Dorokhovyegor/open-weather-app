package com.dorokhov.openweatherapp.repository.main

import androidx.lifecycle.LiveData
import com.dorokhov.openweatherapp.api.WeatherApiService
import com.dorokhov.openweatherapp.api.responses.WeatherResponse
import com.dorokhov.openweatherapp.model.WeatherModel
import com.dorokhov.openweatherapp.repository.JobManager
import com.dorokhov.openweatherapp.repository.NetworkBoundResource
import com.dorokhov.openweatherapp.ui.DataState
import com.dorokhov.openweatherapp.ui.Response
import com.dorokhov.openweatherapp.ui.ResponseType
import com.dorokhov.openweatherapp.ui.main.weather_main.state.WeatherViewState
import com.dorokhov.openweatherapp.utils.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.collections.ArrayList

class WeatherRepository
@Inject
constructor(
    val networkManager: NetworkManager,
    val weatherApiService: WeatherApiService
) : JobManager("WeatherRepository") {

    fun getWeatherFromCurrentCircle(
        rawString: String?
    ): LiveData<DataState<WeatherViewState>> {

        var inputError = WeatherViewState.InputCoordFields(rawString).isValidForInputCoordinates()
        if (!inputError.equals(WeatherViewState.InputCoordFields.InputCoordinatesError.none())) {
            return returnErrorResponse(inputError, ResponseType.Dialog())
        }

        return object : NetworkBoundResource<WeatherResponse, Any, WeatherViewState>(
            networkManager.isConnectedToTheInternet()
        ) {

            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<WeatherResponse>) {
                val modList: java.util.ArrayList<WeatherModel> = ArrayList()
                response.body.list?.forEach { item ->
                    val newId = item.id
                    val newName = item.name
                    val newTemp = (item.main.temp.minus(273))
                    var newDescription: String? = null
                    var newIcon: String? = null

                    item.weather?.let { list ->
                        if (list.size > 0) {
                            newDescription = list[0]?.description
                            newIcon = Constants.IMAGE_PATH_BASE_URL + list[0]?.icon + "@2x.png"
                        } else {
                            newDescription = ""
                            newIcon = ""
                        }
                    }

                    modList.add(
                        WeatherModel(
                            newId, newName, newTemp.toInt(), newDescription!!, newIcon!!
                        )
                    )
                }
                withContext(Main) {
                    if (response.body.list != null) {
                        onCompleteJob(
                            DataState.data(
                                data = WeatherViewState(
                                    weatherFields = WeatherViewState.WeatherFields(
                                        weatherList = modList
                                    )
                                ),
                                response = null
                            )
                        )
                    } else {
                        onCompleteJob(
                            DataState.data(
                                response = Response(
                                    message = "Список пуст", responseType = ResponseType.Toast()
                                )
                            )
                        )
                    }
                }
            }

            override fun createCall(): LiveData<GenericApiResponse<WeatherResponse>> {

                // если я сюда дошел, я знаю, что координаты ввели верно, почти можно не переживать, но очевидно, что это очень слабое место
                val coord = rawString?.toCoordinates()
                return weatherApiService.getWeatherInCircle(
                    coord?.lat,
                    coord?.lon,
                    15,
                    "ru",
                    Constants.tokenApp
                )
            }


            override fun setJob(job: Job) {
                addJob("getWeatherFromCurrentCircle", job)
            }
        }.asLiveData()
    }

    private fun returnErrorResponse(
        loginFieldError: String,
        responseType: ResponseType
    ): LiveData<DataState<WeatherViewState>> {
        return object : LiveData<DataState<WeatherViewState>>() {
            override fun onActive() {
                super.onActive()
                value = DataState.error(
                    response = Response(
                        loginFieldError,
                        responseType
                    )
                )
            }
        }
    }

}