package com.bernoune.lib.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bernoune.lib.data.entities.CityWeather
import com.bernoune.lib.data.local.AppDatabase.Companion.VERSION
import com.bernoune.lib.data.local.dao.CityWeatherDao
import com.bernoune.lib.data.entities.City
import com.bernoune.lib.data.local.dao.CityDao



@Database(entities = [City::class,CityWeather::class], version = VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val cityWeatherDao: CityWeatherDao
    abstract val cityDao: CityDao

    companion object {
        const val VERSION = 1
        const val NAME = "weather.db"
    }

}