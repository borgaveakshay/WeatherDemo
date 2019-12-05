package com.gojek.waetherdemo.di.module

import android.content.Context
import androidx.room.Room
import com.gojek.data.db.WeatherDao
import com.gojek.data.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule(private val context: Context) {


    @Singleton
    @Provides
    fun getContext(): Context = context

    @Provides
    @Singleton
    fun getDao(): WeatherDao {
        return getDatabase().getWeatherDao()
    }


    @Singleton
    @Provides
    fun getDatabase(): WeatherDatabase {

        return Room.databaseBuilder(
            getContext(),
            WeatherDatabase::class.java, "WeatherDb"
        ).build()
    }
}