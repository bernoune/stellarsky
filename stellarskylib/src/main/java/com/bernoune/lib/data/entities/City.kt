package com.bernoune.lib.data.entities

import androidx.room.Entity
import com.google.gson.Gson
import org.json.JSONArray
import java.io.Serializable



@Entity(tableName = "cities", primaryKeys = ["lat", "lng"])

class City : Serializable {

    var country: String? = null
    var city: String? = null
    var lat: Double = 0.0
    var lng: Double = 0.0

    constructor(country: String?, city: String?, lat: Double, lng: Double) {
        this.country = country
        this.city = city
        this.lat = lat
        this.lng = lng
    }


    override fun toString(): String {
        return "City(country=$country, city=$city, lat=$lat, lng=$lng)"
    }

    companion object {
        fun buildCityFromJson(cityJsonString: String?): MutableList<City> {
            val list = mutableListOf<City>()
            val jsonArray = JSONArray(cityJsonString)

            for (index in 0..jsonArray.length().minus(1)) {
                list.add(Gson().fromJson(jsonArray.getString(index), City::class.java))
            }

            return list
        }
    }
}