package com.bernoune.stellarsky.di

import com.bernoune.lib.di.component.AppScope
import com.bernoune.lib.di.component.LibComponent
import com.bernoune.stellarsky.common.base.BaseApplication
import com.bernoune.stellarsky.di.module.AppModule
import com.bernoune.stellarsky.feature.launcher.LauncherViewModel
import com.bernoune.stellarsky.feature.weather.WeatherViewModel
import dagger.Component



@AppScope
@Component(
    dependencies = [LibComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(application: BaseApplication)
    fun inject(weatherViewModel: WeatherViewModel)
    fun inject(launcherViewModel: LauncherViewModel)
}
