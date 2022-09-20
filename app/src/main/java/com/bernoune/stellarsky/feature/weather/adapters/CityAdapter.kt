package com.bernoune.stellarsky.feature.weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bernoune.lib.data.entities.City
import com.bernoune.stellarsky.R
import com.bernoune.stellarsky.feature.weather.WeatherContract
import kotlinx.android.synthetic.main.item_city.view.*


class CityAdapter(private val listener: WeatherContract.ListCityFragmentContract) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {


    /**
    * PROPERTIES SECTION
    */
    private val cityList: MutableList<City> = mutableListOf()

    fun setCityListData(list: List<City>) {
        cityList.clear()
        cityList.addAll(list)
        notifyDataSetChanged()
    }

    /**
    * VIEW HOLDER
    */
    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cityTextView: TextView = view.city_name
        fun bindDataToAssociatedView(city: City?) {
            cityTextView.text = city?.city
            cityTextView.setOnClickListener {
                city?.lat?.let { it1 ->
                    city.city?.let { it2 ->
                        listener.clickToGetWeatherDetail(
                            it1, city.lng,
                            it2
                        )
                    }
                }
            }
        }
    }

    /**
    * IMPLEMENTATION SECTION
    */

    override fun getItemCount(): Int = cityList.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindDataToAssociatedView(cityList[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityViewHolder = CityViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)
    )

}