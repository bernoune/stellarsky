package com.bernoune.lib.domain

import com.bernoune.lib.data.repository.WeatherRepository
import javax.inject.Inject


class GetWeatherInfoFromLocal @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun execute(lat: Double, lon: Double) =
        weatherRepository.getWeatherInfoFromLocal(lat, lon)
}