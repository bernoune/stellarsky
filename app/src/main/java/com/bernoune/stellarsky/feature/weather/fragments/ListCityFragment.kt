package com.bernoune.stellarsky.feature.weather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bernoune.stellarsky.R
import com.bernoune.stellarsky.feature.weather.WeatherActivity
import com.bernoune.stellarsky.feature.weather.WeatherContract
import com.bernoune.stellarsky.feature.weather.WeatherViewModel
import com.bernoune.stellarsky.feature.weather.adapters.CityAdapter
import kotlinx.android.synthetic.main.fragment_list_city.*


class ListCityFragment : Fragment(),
    WeatherContract.ListCityFragmentContract {

    /**
    * VIEWMODEL
    */
    private lateinit var weatherViewModel: WeatherViewModel

    /**
    * PROPERTIES SECTION
    */
    private var activityContractImp: WeatherContract.WeatherActivityContract? = null
    private var adapter: CityAdapter? = null

    /**
    * FRAGMENT LIFECYCLE HANDLING
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { _ ->
            weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_city, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityContractImp = activity as WeatherActivity
        initRecyclerView()
        getCityListFromLocal()
        initObservation()
        clickToAddNewCity()
    }

    override fun onResume() {
        super.onResume()
        initToolbar()
    }


    /**
    * ListCityFragmentContract IMPLEMENTATION
    */
    override fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        city_recyclerView.layoutManager = linearLayoutManager
        adapter = CityAdapter(this)
        city_recyclerView.adapter = adapter

        city_recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

    }

    override fun initToolbar() {
        activityContractImp?.initToolbar("ListCity")
    }

    override fun initObservation() {

        weatherViewModel.observeFavoritesCitiesLiveData()
            .observe(viewLifecycleOwner, Observer { list ->
                list?.let {
                    adapter?.setCityListData(it)
                }

            })
    }

    override fun getCityListFromLocal() {
        weatherViewModel.getFavoritesCitiesFromLocal()
    }

    override fun clickToAddNewCity() {
        add_city.setOnClickListener {
            activityContractImp?.navigateTo(AddCityFragment.newInstance(), true)
        }
    }

    override fun clickToGetWeatherDetail(lat: Double, lon: Double, city: String) {
        activityContractImp?.navigateTo(WeatherDetailFragment.newInstance(lat, lon, city), true)
    }


    companion object {
        fun newInstance() = ListCityFragment()

    }

}