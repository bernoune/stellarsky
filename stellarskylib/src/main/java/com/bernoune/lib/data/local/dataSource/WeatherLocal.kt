package com.bernoune.lib.data.local.dataSource

import com.bernoune.lib.data.entities.CityWeather
import com.bernoune.lib.data.local.AppDatabase
import com.bernoune.lib.data.entities.City
import javax.inject.Inject



class WeatherLocal @Inject constructor() {
    @Inject
    lateinit var appDatabase: AppDatabase

    suspend fun findAllCityWeather(lat: Double, lon: Double): CityWeather? =
        appDatabase.cityWeatherDao.findAll(lat, lon)

    suspend fun addCityWeather(cityWeather: CityWeather) {
        appDatabase.cityWeatherDao.add(cityWeather)
    }

    suspend fun addCity(city: City) {
        appDatabase.cityDao.insert(city)
    }

    suspend fun findAllFavoritesCities() = appDatabase.cityDao.findAll()

}