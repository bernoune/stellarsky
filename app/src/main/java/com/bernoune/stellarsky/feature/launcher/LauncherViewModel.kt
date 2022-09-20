package com.bernoune.stellarsky.feature.launcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*



class LauncherViewModel : ViewModel(), LauncherContract.LauncherViewModelContract {


    /**
    * LIVEDATA OBSERVATION SECTION
    */

    private var closingEvent: MutableLiveData<Boolean> = MutableLiveData()
    override fun observeGoToHomeAndClose(): LiveData<Boolean> = closingEvent


    /**
    * INIT SECTION
    */

    init {
        observeDelay()
    }

    override fun observeDelay() {
        viewModelScope.launch {
            delay(LAUNCHER_SCREEN_DELAY)
            closingEvent.postValue(true)
        }
    }

    companion object {
        private const val LAUNCHER_SCREEN_DELAY: Long = 3000
    }


}