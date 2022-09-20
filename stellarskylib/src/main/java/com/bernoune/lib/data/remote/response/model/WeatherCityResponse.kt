package com.bernoune.lib.data.remote.response.model

import com.bernoune.lib.data.entities.CityWeather
import com.google.gson.Gson
import com.squareup.moshi.Json




class WeatherCityResponse {
    @Json(name = "lat")
    var lat: Double = 0.0

    @Json(name = "lon")
    var lon: Double = 0.0

    @Json(name = "timezone")
    var timezone: String? = null

    @Json(name = "current")
    var current: Current? = null

    @Json(name = "daily")
    var daily: MutableList<Daily>? = null

    override fun toString(): String =
        "WeatherCityResponse(lat=$lat, lon=$lon, current=$current, daily=$daily)"
}

fun WeatherCityResponse.cityWeatherBuilder(): CityWeather {
    val cityWeather = CityWeather()
    cityWeather.lon = this.lon
    cityWeather.lat = this.lat
    cityWeather.timezone = this.timezone
    cityWeather.current = Gson().toJson(this.current)
    cityWeather.daily = Gson().toJson(this.daily)
    return cityWeather
}
