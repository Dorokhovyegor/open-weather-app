package com.dorokhov.openweatherapp.api.responses


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    @Expose
    val deg: Int?,
    @SerializedName("speed")
    @Expose
    val speed: Int?
)