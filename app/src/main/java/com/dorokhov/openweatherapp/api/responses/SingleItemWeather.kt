package com.dorokhov.openweatherapp.api.responses


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SingleItemWeather(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("main")
    @Expose
    val main: Main,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("weather")
    @Expose
    val weather: List<Weather?>?
)