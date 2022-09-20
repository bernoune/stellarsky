package com.bernoune.lib.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bernoune.lib.data.entities.CityWeather



@Dao
interface CityWeatherDao {

    @Query("SELECT * FROM weather_city_info WHERE lat=:lat AND lon=:lon")
    suspend fun findAll(lat: Double, lon: Double): CityWeather?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(cities: CityWeather)
}