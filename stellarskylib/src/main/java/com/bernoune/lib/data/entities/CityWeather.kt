package com.bernoune.lib.data.entities

import androidx.room.Entity
import com.bernoune.lib.data.remote.response.model.Current
import com.bernoune.lib.data.remote.response.model.Daily
import com.bernoune.lib.data.remote.response.model.WeatherCityResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



@Entity(
    tableName = "weather_city_info",
    primaryKeys = ["lat", "lon"]
)
class CityWeather {
    var lat: Double = 0.0

    var lon: Double = 0.0

    var timezone: String? = null

    var current: String? = null

    var daily: String? = null

    override fun toString(): String =
        "WeatherCityResponse(lat=$lat, lon=$lon, current=$current, daily=$daily)"
}

fun CityWeather.cityWeatherBuilder(): WeatherCityResponse {
    val weatherCityResponse = WeatherCityResponse()
    weatherCityResponse.lon = lon
    weatherCityResponse.lat = lat
    weatherCityResponse.timezone = this.timezone
    weatherCityResponse.current = Gson().fromJson(this.current, Current::class.java)
    weatherCityResponse.daily =
        Gson().fromJson(this.daily, object : TypeToken<List<Daily>>() {}.type)
    return weatherCityResponse
}