package com.bernoune.lib.di.modules
import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class ContextModule(private val application: Application) {

    /**
     * Create a provider method binding for [Context].
     *
     * @return Instance of context.
     * @see Provides
     */
    @Provides
    fun provideContext(): Context = application
}
