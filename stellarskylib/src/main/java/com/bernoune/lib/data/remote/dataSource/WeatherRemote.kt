package com.bernoune.lib.data.remote.dataSource

import com.bernoune.lib.data.remote.response.model.WeatherCityResponse
import com.bernoune.lib.data.remote.service.ServiceEndPoint
import javax.inject.Inject



class WeatherRemote @Inject constructor() {

    @Inject
    lateinit var serviceEndPoint: ServiceEndPoint

    suspend fun getCityWeatherAsync(
        lat: Double,
        lon: Double,
        exclude: String,
        appId: String,
        units: String,
        lang: String
    ): WeatherCityResponse = serviceEndPoint.getCityWeather(lat, lon, exclude, appId, units, lang)
}
