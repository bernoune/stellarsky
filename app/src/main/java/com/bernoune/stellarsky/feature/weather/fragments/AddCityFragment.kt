package com.bernoune.stellarsky.feature.weather.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bernoune.lib.data.entities.City
import com.bernoune.lib.data.entities.City.Companion.buildCityFromJson
import com.bernoune.stellarsky.R
import com.bernoune.stellarsky.common.extensions.loadJSONFromAsset
import com.bernoune.stellarsky.feature.weather.WeatherActivity
import com.bernoune.stellarsky.feature.weather.WeatherContract
import com.bernoune.stellarsky.feature.weather.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_add_city.*

class AddCityFragment : Fragment(),
    WeatherContract.AddCityFragmentContract {


    /**
    * VIEWMODEL
    */
    private lateinit var weatherViewModel: WeatherViewModel

    /**
    * PROPERTIES SECTION
    */
    private lateinit var listOfCities: MutableList<City>
    private var activityContractImp: WeatherContract.WeatherActivityContract? = null

    /**
    * FRAGMENT LIFECYCLE HANDLING
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { _ ->
            weatherViewModel =  ViewModelProvider(this).get(WeatherViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_city, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityContractImp = activity as WeatherActivity

        Handler(Looper.getMainLooper()).postDelayed({
            val cityString = context?.loadJSONFromAsset()
            listOfCities = buildCityFromJson(cityString)
            cityView.initViewAccordingTo(listOfCities, this)
        }, 777)


        search?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.i("Bottom Sheet", "" + s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i("Bottom Sheet", "" + s)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cityView?.search(s.toString())
            }
        })
    }


    override fun onResume() {
        super.onResume()
        initToolbar()
    }

    /**
    * AddCityFragmentContract IMPLEMENTATION
    */
    override fun initToolbar() {
        activityContractImp?.initToolbar("addCity")
    }
    override fun selectCity(city: City?) {
        // add city to data base
        city?.let {
            weatherViewModel.addCity(it)
            // notify activity to back to cities list screen
            activityContractImp?.popBackStack()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = AddCityFragment()
    }


}