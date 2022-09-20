package com.bernoune.lib.domain

import com.bernoune.lib.data.repository.WeatherRepository
import com.bernoune.lib.data.entities.City
import javax.inject.Inject



class AddCity @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun execute(city: City) =
        weatherRepository.addCity(city)

}