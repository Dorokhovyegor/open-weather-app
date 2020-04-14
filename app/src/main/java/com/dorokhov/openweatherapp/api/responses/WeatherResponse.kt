package com.dorokhov.openweatherapp.api.responses


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// созданно автоматически, юзал плагин

data class WeatherResponse(
    @SerializedName("cod")
    @Expose
    val cod: String?,
    @SerializedName("count")
    @Expose
    val count: Int?,
    @SerializedName("list")
    @Expose
    val list: List<SingleItemWeather>?,
    @SerializedName("message")
    @Expose
    val message: String?
)