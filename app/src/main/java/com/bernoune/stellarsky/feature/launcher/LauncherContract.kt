package com.bernoune.stellarsky.feature.launcher

import androidx.lifecycle.LiveData



object LauncherContract {
    interface LauncherViewModelContract {
        fun observeGoToHomeAndClose(): LiveData<Boolean>
        fun observeDelay()
    }

    interface LauncherActivityContract {
        fun initObservation()
        fun navigateToHome()
    }
}