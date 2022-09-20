package com.bernoune.stellarsky.feature.weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bernoune.lib.data.remote.response.model.Daily
import com.bernoune.stellarsky.R
import com.bernoune.stellarsky.common.extensions.defineDrawableFrom
import com.bernoune.stellarsky.common.extensions.getDateTime
import kotlinx.android.synthetic.main.item_daily_weather.view.*
import kotlin.math.roundToInt



class DailyWeatherAdapter : RecyclerView.Adapter<DailyWeatherAdapter.DailyViewHolder>() {



    private val dailyList: MutableList<Daily> = mutableListOf()

    fun defineDailyListData(list: MutableList<Daily>) {
        dailyList.clear()
        dailyList.addAll(list)
        notifyDataSetChanged()
    }

    /**
    * VIEW HOLDER
    */
    inner class DailyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateTv: TextView = view.daily_weather_date
        private val tempTv: TextView = view.daily_weather_temp
        private val weatherImg: ImageView = view.daily_weather_image

        fun bindDataToAssociatedView(daily: Daily?) {
            tempTv.text =
                tempTv.context.resources.getString(
                    R.string.temp_min_max_degree_placeholder,
                    daily?.temp?.min?.roundToInt().toString(),
                    daily?.temp?.max?.roundToInt().toString()
                )

            dateTv.text = daily?.dt?.getDateTime()

            weatherImg.defineDrawableFrom(daily?.weather?.get(0)?.icon)
        }
    }

    /**
    * IMPLEMENTATION SECTION
    */

    override fun getItemCount(): Int = dailyList.size

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bindDataToAssociatedView(dailyList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder =
        DailyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_daily_weather, parent, false)
        )

}