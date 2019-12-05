package com.gojek.data.repositories

import com.gojek.domain.datastore.WeatherDataRepo
import com.gojek.domain.entities.CityWeather
import com.gojek.domain.entities.request.WeatherRequest
import com.gojek.domain.repository.WeatherRepository
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepositoryImpl (private val weatherDataStore: WeatherDataRepo) : WeatherRepository {

    override fun getWeatherUpdate(request: WeatherRequest?): Observable<CityWeather> {
        return weatherDataStore.getWeatherUpdate(request)
    }
}