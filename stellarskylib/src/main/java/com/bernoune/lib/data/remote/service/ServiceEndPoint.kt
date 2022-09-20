package com.bernoune.lib.data.remote.service

import com.bernoune.lib.data.remote.response.model.WeatherCityResponse
import retrofit2.http.GET
import retrofit2.http.Query



interface ServiceEndPoint {

    companion object {

        /**
        * ENDPOINTS
        */
        const val ONE_CALL = "onecall"

        /**
        * PARAMS
        */
        const val LON = "lon"
        const val LAT = "lat"
        const val EXCLUDE = "exclude"
        const val APPID = "appid"
        const val UNITS = "units"
        const val LANGUAGE = "lang"
    }


    /**
    * method
    */
    @GET(ONE_CALL)
    suspend fun getCityWeather(
        @Query(LAT) lat: Double,
        @Query(LON) lon: Double,
        @Query(EXCLUDE) exclude: String,
        @Query(APPID) appid: String,
        @Query(UNITS) units : String,
        @Query(LANGUAGE) lang : String
    ): WeatherCityResponse


}