package com.dorokhov.openweatherapp.utils

import com.dorokhov.openweatherapp.api.responses.Coord
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.toCoordinates(): Coord{
    return Coord(
        this.split(",")[0].toDouble(),
        this.split(",")[1].toDouble()
    )
}