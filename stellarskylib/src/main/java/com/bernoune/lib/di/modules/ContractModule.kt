package com.bernoune.lib.di.modules

import com.bernoune.lib.data.local.dataSource.WeatherLocal
import com.bernoune.lib.data.remote.dataSource.WeatherRemote
import com.bernoune.lib.data.repository.WeatherRepositoryImpl
import com.bernoune.lib.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ContractModule {

    /**
     * Create a provider method binding for [WeatherRepository].
     *
     * @return Instance of WeatherRepository.
     * @see Provides
     */

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRemote: WeatherRemote, weatherLocal: WeatherLocal)
            : WeatherRepository = WeatherRepositoryImpl(weatherRemote, weatherLocal)
}