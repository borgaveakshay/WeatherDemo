package com.gojek.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.gojek.domain.entities.CityWeather
import com.gojek.domain.entities.Temperature

@Dao
interface WeatherDao {

    fun updateWeather(cityWeather: CityWeather): LiveData<CityWeather>
}


@Database(entities = [CityWeather::class, Temperature::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDao
}

