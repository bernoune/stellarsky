package com.bernoune.stellarsky.common.base

import android.app.Application
import com.bernoune.lib.di.component.DaggerLibComponent
import com.bernoune.lib.di.component.LibComponent
import com.bernoune.lib.di.modules.ContextModule
import com.bernoune.stellarsky.di.AppComponent
import com.bernoune.stellarsky.di.DaggerAppComponent


class BaseApplication : Application() {

    /**
    * COMPANION OBJECT SECTION
    */
    companion object {

        /**
         * Obtain app  components.
         * libComponent and appComponent
         */
        lateinit var libComponent: LibComponent
        lateinit var appComponent: AppComponent



    }

    override fun onCreate() {
        super.onCreate()
        initLibDependencyInjection()
        initAppDependencyInjection()
    }

    /**
    * PRIVATE INIT METHODS
    */

    /**
     * Initialize app dependency injection component.
     */
    private fun initAppDependencyInjection() {
        appComponent = DaggerAppComponent
            .builder()
            .libComponent(libComponent)
            .build()

        appComponent.inject(this)
    }

    /**
     * Initialize Lib dependency injection component.
     */
    private fun initLibDependencyInjection() {
        libComponent = DaggerLibComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

}
