package com.bernoune.lib.data.local.converters

import androidx.room.TypeConverter
import com.bernoune.lib.data.remote.response.model.Current
import com.bernoune.lib.data.remote.response.model.Daily
import com.bernoune.lib.data.remote.response.model.DailyWeather
import com.bernoune.lib.data.remote.response.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*



open class Converters {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? = if (timestamp == null) null else Date(timestamp)

    @TypeConverter
    fun toTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun toList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    /**
    * CURRENT OBJECT CONVERTER
    */
    @TypeConverter
    fun stringToWeather(value: String): Weather = Gson().fromJson(value, Weather::class.java)

    @TypeConverter
    fun currentToString(weather: Weather): String {
        val gson = Gson()
        return gson.toJson(weather)
    }
    @TypeConverter
    fun stringToDailyWeather(value: String): DailyWeather = Gson().fromJson(value, DailyWeather::class.java)

    @TypeConverter
    fun dailyWeatherToString(dailyWeather: DailyWeather): String {
        val gson = Gson()
        return gson.toJson(dailyWeather)
    }

    @TypeConverter
    fun stringToDaily(value: String): Daily = Gson().fromJson(value, Daily::class.java)

    @TypeConverter
    fun dailyToString(daily: Daily): String {
        val gson = Gson()
        return gson.toJson(daily)
    }

    @TypeConverter
    fun stringToCurrent(value: String): Current = Gson().fromJson(value, Current::class.java)

    @TypeConverter
    fun currentToString(current: Current): String {
        val gson = Gson()
        return gson.toJson(current)
    }
}