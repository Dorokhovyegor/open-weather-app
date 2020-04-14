package com.dorokhov.openweatherapp.api

import androidx.lifecycle.LiveData
import com.dorokhov.openweatherapp.api.responses.WeatherResponse
import com.dorokhov.openweatherapp.utils.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("/data/2.5/find/")
    fun getWeatherInCircle(
        @Query("lat") lat: Double? = 34.4,
        @Query("lon") lon: Double? = 33.4,
        @Query("cnt") count: Int? = 20,
        @Query("lang") lang: String = "ru",
        @Query("APPID") tokenApp: String
    ): LiveData<GenericApiResponse<WeatherResponse>>

}