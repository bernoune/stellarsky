package com.bernoune.lib.domain

import com.bernoune.lib.data.repository.WeatherRepository
import javax.inject.Inject


class GetFavoritesCitiesFromLocal @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun execute() = weatherRepository.getFavoriteCitiesFromLocal()

}