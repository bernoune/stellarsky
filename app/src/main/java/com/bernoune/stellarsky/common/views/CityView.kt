package com.bernoune.stellarsky.common.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bernoune.lib.data.entities.City
import com.bernoune.stellarsky.R
import com.bernoune.stellarsky.feature.weather.WeatherContract
import com.bernoune.stellarsky.feature.weather.adapters.CityListAdapter
import kotlinx.android.synthetic.main.all_cities_view.view.*
import java.util.*


class CityView : ConstraintLayout {

    /**
    * GENERAL PROPERTY SECTION
    */
    private var adapter: CityListAdapter? = null
    private var citiesList: MutableList<City>? = null
    private var selectedCitiesList: MutableList<City>? = null

    /**
    * VIEW ATTRIBUTES SECTION
    */
    private var recyclerView: RecyclerView? = null

    /**
    * INTERACTION PROPERTY SECTION
    */
    var cityViewInteraction: WeatherContract.AddCityFragmentContract? = null


    constructor(@NonNull context: Context) : super(context)

    constructor(@NonNull context: Context, @NonNull attr: AttributeSet) : super(context, attr) {
        val view = LayoutInflater.from(context).inflate(R.layout.all_cities_view, this)
        recyclerView = view.recycleView_city
    }

    fun initViewAccordingTo(
        listOfCities: MutableList<City>,
        interaction: WeatherContract.AddCityFragmentContract
    ) {
        citiesList = listOfCities
        selectedCitiesList = mutableListOf()
        selectedCitiesList?.addAll(citiesList ?: arrayListOf())
        cityViewInteraction = interaction
        initCountryList()
    }

    /**
    * INTERACTION VIEW HANDLING SECTION
    */
    private fun initCountryList() {
        context?.let { ctx ->
            adapter =
                CityListAdapter(
                    selectedCitiesList,
                    object :
                        CityListAdapter.ItemClickEvent {
                        override fun onItemClick(city: City?) {
                            cityViewInteraction?.selectCity(city)
                        }
                    }
                )
            val llm = LinearLayoutManager(ctx)
            llm.orientation = LinearLayoutManager.VERTICAL
            recyclerView?.layoutManager = llm
            recyclerView?.adapter = adapter
        }
    }

    @SuppressLint("DefaultLocale")
    fun search(text: String) {
        selectedCitiesList?.clear()
        citiesList?.let { list ->
            for (country in list) {
                buildCityListFromCityDataAndSearchedWord(text, country)
            }
        }
        adapter?.notifyDataSetChanged()
    }

    private fun buildCityListFromCityDataAndSearchedWord(text: String, city: City) {
        city.city?.toLowerCase(Locale.getDefault())
            ?.contains(text.toLowerCase(Locale.getDefault()))?.let { doesContain ->
                if (doesContain) selectedCitiesList?.add(city)
            }
    }

}