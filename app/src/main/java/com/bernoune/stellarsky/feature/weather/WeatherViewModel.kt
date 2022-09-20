package com.bernoune.stellarsky.feature.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bernoune.lib.data.entities.City
import com.bernoune.lib.data.entities.CityWeather
import com.bernoune.lib.data.entities.cityWeatherBuilder
import com.bernoune.lib.data.remote.response.model.WeatherCityResponse
import com.bernoune.lib.data.remote.response.model.cityWeatherBuilder
import com.bernoune.lib.domain.*
import com.bernoune.stellarsky.common.base.BaseApplication
import com.bernoune.stellarsky.common.utils.LoadingState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject



class WeatherViewModel @Inject constructor() : ViewModel(),
    WeatherContract.WeatherViewModelContract {


    /**
    * USE CASE INJECTION SECTION
    */
    init {

        BaseApplication.appComponent.inject(this)
    }

    @Inject
    lateinit var getCityWeather: GetCityWeather

    @Inject
    lateinit var addWeatherInfoToLocal: AddWeatherInfoToLocal

    @Inject
    lateinit var getWeatherInfoFromLocal: GetWeatherInfoFromLocal

    @Inject
    lateinit var addCity: AddCity

    @Inject
    lateinit var getFavoritesCitiesFromLocal: GetFavoritesCitiesFromLocal

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState


    /**
    * OBSERVE FUNCTION SECTION
    */


    private val weatherDetailLiveData = MutableLiveData<WeatherCityResponse?>()
    fun observeWeatherDetailLiveData(): LiveData<WeatherCityResponse?> = weatherDetailLiveData

    private val favoritesCitiesLiveData = MutableLiveData<List<City>?>()
    fun observeFavoritesCitiesLiveData(): LiveData<List<City>?> = favoritesCitiesLiveData


    /**
    * WeatherViewModelContract IMPLEMENTATION
    */
    override fun fetchData(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                val weatherAsync = async { getCityWeather.execute(lat, lon) }
                val weather = weatherAsync.await()
                addWeatherInfoToLocal(weather.cityWeatherBuilder())
                weatherDetailLiveData.postValue(weather)
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }

    }

    private fun addWeatherInfoToLocal(cityWeather: CityWeather) {
        viewModelScope.launch {
            addWeatherInfoToLocal.execute(cityWeather)
        }
    }

    override fun retrieveWeatherInfoFromLocal(lat: Double, lon: Double) {
        viewModelScope.launch {
            val weatherAsync = async { getWeatherInfoFromLocal.execute(lat, lon) }
            val weather = weatherAsync.await()
            weather?.let {
                weatherDetailLiveData.postValue(it.cityWeatherBuilder())
            }

        }
    }

    override fun addCity(city: City) {
        viewModelScope.launch {
            addCity.execute(city)
        }
    }

    override fun getFavoritesCitiesFromLocal() {
        viewModelScope.launch {
            val citiesAsync = async { getFavoritesCitiesFromLocal.execute() }
            val cities = citiesAsync.await()
            favoritesCitiesLiveData.postValue(cities)
        }
    }


}
