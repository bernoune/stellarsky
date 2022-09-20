package com.bernoune.lib.di.component

import android.content.Context
import com.bernoune.lib.data.local.AppDatabase
import com.bernoune.lib.di.modules.ContextModule
import com.bernoune.lib.di.modules.ContractModule
import com.bernoune.lib.di.modules.DatabaseModule
import com.bernoune.lib.di.modules.NetworkModule
import com.bernoune.lib.data.remote.service.ServiceEndPoint
import com.bernoune.lib.data.repository.WeatherRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        ContractModule::class
    ]
)
interface LibComponent {

    fun context(): Context

    /**
     * Provide dependency graph
     *
     * @return ServiceEndPoint
     */
    fun serviceEndPoint(): ServiceEndPoint

    /**
     * Provide dependency graph
     * @return AppDatabase
     */
    fun appDatabase(): AppDatabase

    /**
     * Provide dependency graph
     *
     * @return WeatherRepository
     */
    fun weatherRepository(): WeatherRepository


}
