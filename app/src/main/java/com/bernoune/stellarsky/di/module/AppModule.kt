package com.bernoune.stellarsky.di.module

import android.content.Context
import com.bernoune.lib.di.component.AppScope
import com.bernoune.stellarsky.common.base.BaseApplication
import dagger.Module
import dagger.Provides



@Module
class AppModule {
    @AppScope
    @Provides
    fun provideContext(application: BaseApplication): Context = application.applicationContext
}