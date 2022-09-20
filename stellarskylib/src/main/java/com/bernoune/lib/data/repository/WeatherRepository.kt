package com.bernoune.lib.data.repository

import com.bernoune.lib.data.entities.CityWeather
import com.bernoune.lib.data.remote.response.model.WeatherCityResponse
import com.bernoune.lib.data.entities.City



interface WeatherRepository {


    suspend fun getCityWeather(
        lat: Double,
        lon: Double,
        exclude: String,
        appid: String,
        units: String,
        lang: String
    ): WeatherCityResponse


    suspend fun getWeatherInfoFromLocal(
        lat: Double,
        lon: Double
    ): CityWeather?

    suspend fun addWeatherInfoToLocal(cityWeather: CityWeather)

    suspend fun addCity(city: City)

    suspend fun getFavoriteCitiesFromLocal(
    ): List<City>
}