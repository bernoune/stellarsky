package com.bernoune.stellarsky.feature.weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bernoune.lib.data.entities.City
import com.bernoune.stellarsky.R
import kotlinx.android.synthetic.main.city_row.view.*


class CityListAdapter(
    var cities: MutableList<City>?,
    private var itemClickEvent: ItemClickEvent
) :
    RecyclerView.Adapter<CityListAdapter.MyHolder>() {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.row_title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.city_row, parent, false)
        return MyHolder(
            v
        )
    }

    override fun getItemCount(): Int = cities?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.title.text = cities?.get(position)?.city
        holder.itemView.setOnClickListener {
            itemClickEvent.onItemClick(cities?.get(position))
        }
    }

    interface ItemClickEvent {
        fun onItemClick(city: City?)
    }
}
