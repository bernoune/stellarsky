package com.bernoune.lib.di.modules
import android.content.Context
import androidx.room.Room
import com.bernoune.lib.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    /**
     * Create a provider method binding for [AppDatabase].
     *
     * @return Instance of AppDatabase.
     * @see Provides
     */

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
