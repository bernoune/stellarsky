package com.bernoune.lib.data.remote.response.model

import com.squareup.moshi.Json

data class Current(

    @Json(name = "dt")
    var dt: Long? = null,

    @Json(name = "temp")
    var temp: Float? = null,

    @Json(name = "weather")
    var weather: MutableList<Weather>? = null,

    var weatherCityResponseId: Int? = null
)

