package com.bernoune.stellarsky.feature.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bernoune.stellarsky.R
import com.bernoune.stellarsky.common.constants.ADD_CITY
import com.bernoune.stellarsky.common.constants.DETAIL_WEATHER
import com.bernoune.stellarsky.common.constants.LIST_CITY
import com.bernoune.stellarsky.common.extensions.replaceFragmentSafely
import com.bernoune.stellarsky.common.views.ToolbarView
import com.bernoune.stellarsky.feature.weather.fragments.ListCityFragment
import kotlinx.android.synthetic.main.activity_main.*

class WeatherActivity : AppCompatActivity(),
    ToolbarView.ToolbarInteraction,
    WeatherContract.WeatherActivityContract {

    /**
    * ACTIVITY LIFECYCLE HANDLING
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.toolbarInteraction = this
        //commit first fragment
        navigateTo(ListCityFragment.newInstance(), true)

    }

    override fun navigateTo(fragment: Fragment, shouldAddToBackStack: Boolean) {
        replaceFragmentSafely(
            R.id.container,
            fragment, fragment::class.java.name,
            addToBackStack = shouldAddToBackStack,
            allowStateLoss = false
        )
    }


    override fun initToolbar(tag: String) {
        when (tag) {
            ADD_CITY -> {
                toolbar.setTitle(getString(R.string.add_city_title))
                toolbar.showPrevious()
            }
            LIST_CITY -> {
                toolbar.setTitle(getString(R.string.list_city))
                toolbar.hidePrevious()
            }
            DETAIL_WEATHER -> {
                toolbar.setTitle(getString(R.string.weather_details))
                toolbar.showPrevious()
            }
        }
    }

    /**
     * go back to the previous fragment
     */
    override fun popBackStack() {
        supportFragmentManager.popBackStack()
    }

    override fun onBack() {
        supportFragmentManager.popBackStack()
    }


}