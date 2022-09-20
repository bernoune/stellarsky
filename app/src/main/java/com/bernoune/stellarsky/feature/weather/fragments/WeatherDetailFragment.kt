package com.bernoune.stellarsky.feature.weather.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bernoune.lib.data.remote.response.model.WeatherCityResponse
import com.bernoune.stellarsky.R
import com.bernoune.stellarsky.common.extensions.defineDrawableFrom
import com.bernoune.stellarsky.common.extensions.getDateTime
import com.bernoune.stellarsky.common.extensions.round
import com.bernoune.stellarsky.common.utils.LoadingState
import com.bernoune.stellarsky.common.utils.isInternetAvailable
import com.bernoune.stellarsky.feature.weather.WeatherActivity
import com.bernoune.stellarsky.feature.weather.WeatherContract
import com.bernoune.stellarsky.feature.weather.WeatherViewModel
import com.bernoune.stellarsky.feature.weather.adapters.DailyWeatherAdapter
import kotlinx.android.synthetic.main.fragment_weather_detail.*
import kotlin.math.roundToInt


class WeatherDetailFragment : Fragment(),
    WeatherContract.WeatherDetailFragmentContract {

    /**
    * VIEWMODEL
    */
    private lateinit var weatherViewModel: WeatherViewModel


    /**
    * PROPERTIES SECTION
    */
    private var lat: Double = 0.0
    private var lon: Double = 0.0
    private var city: String = ""
    private var adapter: DailyWeatherAdapter? = null
    private var activityContractImp: WeatherContract.WeatherActivityContract? = null


    /**
    * FRAGMENT LIFECYCLE HANDLING
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { _ ->
            weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        }
        //weatherViewModel = viewModel()
        arguments?.let {
            lat = it.getDouble(ARG_PARAM_lat)
            lon = it.getDouble(ARG_PARAM_lon)
            city = it.getString(ARG_PARAM_city).toString()
        }
        initObservation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_weather_detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityContractImp = activity as WeatherActivity
        initDailyWeatherRecyclerView()
        // check if there is connection
        if (context?.let { isInternetAvailable(it) }!!) weatherViewModel.fetchData(lat, lon)
        else weatherViewModel.retrieveWeatherInfoFromLocal(lat.round(2), lon.round(2))


    }

    override fun onResume() {
        super.onResume()
        initToolbar()
    }


    /**
    * IMPLEMENTATION
    */
    override fun initObservation() {
        weatherViewModel.observeWeatherDetailLiveData()
            .observe(this, Observer { result ->
                result?.let { weatherResponseObject ->
                    defineCurrentWeather(weatherResponseObject)
                    weatherResponseObject.daily?.let { dailyList ->
                        daily_weather_title.text =
                            getString(R.string.temp_daily_title_degree_placeholder, dailyList.size)
                        adapter?.defineDailyListData(
                            dailyList
                        )
                    }
                }
            })

        weatherViewModel.loadingState.observe(this, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> Toast.makeText(context, it.msg, Toast.LENGTH_SHORT)
                    .show()
                LoadingState.Status.RUNNING -> Log.d("TAG", "initObservation: Loading in progress ...")
                LoadingState.Status.SUCCESS -> Log.d("TAG", "initObservation: Loaded Successfully")
            }
        })
    }

    override fun initToolbar() {
        activityContractImp?.initToolbar("DetailWeather")
    }


    /**
    * UI HANDLING
    */
    override fun defineCurrentWeather(cityWeatherResponse: WeatherCityResponse) {
        current_temp.text = getString(
            R.string.temp_degree_placeholder,
            cityWeatherResponse.current?.temp?.roundToInt().toString()
        )
        current_city.text = city
        current_date.text =
            getString(R.string.temp_last_update_placeholder, cityWeatherResponse.current?.dt?.getDateTime())
        current_desc.text = cityWeatherResponse.current?.weather?.get(0)?.main
        current_weather_image.defineDrawableFrom(cityWeatherResponse.current?.weather?.get(0)?.icon)
    }

    override fun initDailyWeatherRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        daily_weather_recycler_view.layoutManager = linearLayoutManager
        adapter = DailyWeatherAdapter()
        daily_weather_recycler_view.adapter = adapter

        daily_weather_recycler_view.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    companion object {

        private const val ARG_PARAM_lat = "ARG_PARAM_lat"
        private const val ARG_PARAM_lon = "ARG_PARAM_lon"
        private const val ARG_PARAM_city = "ARG_PARAM_city"

        @JvmStatic
        fun newInstance(lat: Double, lon: Double, city: String) = WeatherDetailFragment().apply {
            arguments = Bundle().apply {
                putDouble(ARG_PARAM_lat, lat)
                putDouble(ARG_PARAM_lon, lon)
                putString(ARG_PARAM_city, city)
            }
        }
    }
}