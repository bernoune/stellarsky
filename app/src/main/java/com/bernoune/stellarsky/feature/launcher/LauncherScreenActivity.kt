package com.bernoune.stellarsky.feature.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bernoune.stellarsky.R
import com.bernoune.stellarsky.feature.weather.WeatherActivity

class LauncherScreenActivity : AppCompatActivity(), LauncherContract.LauncherActivityContract {
    /**
    * PROPERTIES SECTION
    */
    private lateinit var launcherViewModel: LauncherContract.LauncherViewModelContract

    /**
    * ACTIVITY LIFECYCLE HANDLING
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher_screen)
        launcherViewModel = ViewModelProvider(this).get(LauncherViewModel::class.java)
        initObservation()
    }

    /**
    * SplachActivityContract IMPLEMENTATION
    */
    override fun initObservation() {
        launcherViewModel
            .observeGoToHomeAndClose()
            .observe(this, Observer {
                navigateToHome()
            })
    }

    override fun navigateToHome() {
        val intent = Intent(this, WeatherActivity::class.java)
        startActivity(intent)
        finish()
    }
}