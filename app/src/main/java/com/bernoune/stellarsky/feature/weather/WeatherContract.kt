package com.bernoune.stellarsky.feature.weather

import androidx.fragment.app.Fragment
import com.bernoune.lib.data.entities.City
import com.bernoune.lib.data.remote.response.model.WeatherCityResponse

/**
 * As a contract, this object contains all interfaces made to centralize what an Activity, Fragment
 * or ViewModel is about to handle.
 *
 */
object WeatherContract {


    interface WeatherActivityContract {
        fun navigateTo(fragment: Fragment, shouldAddToBackStack: Boolean)
        fun initToolbar(tag: String)
        fun popBackStack()
    }

    interface ListCityFragmentContract {
        fun initToolbar()
        fun initRecyclerView()
        fun initObservation()
        fun getCityListFromLocal()
        fun clickToAddNewCity()
        fun clickToGetWeatherDetail(lat: Double, lon: Double, city: String)
    }

    interface AddCityFragmentContract {
        fun initToolbar()
        fun selectCity(city: City?)
    }

    interface WeatherDetailFragmentContract {
        fun initToolbar()
        fun initObservation()
        fun defineCurrentWeather(cityWeatherResponse: WeatherCityResponse)
        fun initDailyWeatherRecyclerView()
    }

    interface WeatherViewModelContract {
        fun fetchData(lat: Double, lon: Double)
        fun retrieveWeatherInfoFromLocal(lat: Double, lon: Double)
        fun addCity(city: City)
        fun getFavoritesCitiesFromLocal()
    }


}