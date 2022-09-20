package com.bernoune.lib.data.repository

import com.bernoune.lib.data.entities.CityWeather
import com.bernoune.lib.data.local.dataSource.WeatherLocal
import com.bernoune.lib.data.remote.dataSource.WeatherRemote
import com.bernoune.lib.data.remote.response.model.WeatherCityResponse
import com.bernoune.lib.data.entities.City



class WeatherRepositoryImpl(
    private val weatherRemote: WeatherRemote,
    private val weatherLocal: WeatherLocal
) : WeatherRepository {

    override suspend fun getCityWeather(
        lat: Double,
        lon: Double,
        exclude: String,
        appid: String,
        units: String,
        lang: String
    ): WeatherCityResponse = weatherRemote.getCityWeatherAsync(
        lat,
        lon,
        exclude,
        appid,
        units,
        lang
    )

    override suspend fun getWeatherInfoFromLocal(lat: Double, lon: Double): CityWeather? =
        weatherLocal.findAllCityWeather(lat, lon)

    override suspend fun addWeatherInfoToLocal(cityWeather: CityWeather) =
        weatherLocal.addCityWeather(cityWeather)

    override suspend fun addCity(city: City) {
        weatherLocal.addCity(city)
    }

    override suspend fun getFavoriteCitiesFromLocal(): List<City> = weatherLocal.findAllFavoritesCities()

}

