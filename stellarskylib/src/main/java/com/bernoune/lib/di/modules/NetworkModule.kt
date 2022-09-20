package com.bernoune.lib.di.modules
import android.content.Context
import com.bernoune.lib.data.remote.service.NetworkModuleFactory
import com.bernoune.lib.data.remote.service.ServiceEndPoint
import dagger.Module
import dagger.Provides




@Module
class NetworkModule {

    /**
     * Create a provider method binding for [ServiceEndPoint].
     *
     * @return Instance of ServiceEndPoint.
     * @see Provides
     */
    @Provides
    internal fun provideServiceEndPoint(context: Context): ServiceEndPoint =
        NetworkModuleFactory.makeService(context)
}
