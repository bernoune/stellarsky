package com.bernoune.lib.domain

import com.bernoune.lib.data.entities.CityWeather
import com.bernoune.lib.data.repository.WeatherRepository
import javax.inject.Inject



class AddWeatherInfoToLocal @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun execute(cityWeather: CityWeather) =
        weatherRepository.addWeatherInfoToLocal(cityWeather)
}