package com.dorokhov.openweatherapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel(
    var id: Int,
    var city: String,
    var temperature: Int,
    var description: String,
    val icon: String
) : Parcelable