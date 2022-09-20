package com.bernoune.lib.domain

import com.bernoune.lib.common.APPID
import com.bernoune.lib.common.EXCLUDE
import com.bernoune.lib.common.LAN
import com.bernoune.lib.common.UNITS
import com.bernoune.lib.data.remote.response.model.WeatherCityResponse
import com.bernoune.lib.data.repository.WeatherRepository
import javax.inject.Inject


class GetCityWeather @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun execute(
        lat: Double,
        lon: Double
    ): WeatherCityResponse {
        return weatherRepository.getCityWeather(
            lat,
            lon,
            EXCLUDE,
            APPID,
            UNITS,
            LAN
        )
    }
}
