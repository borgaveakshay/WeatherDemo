package com.gojek.domain.datastore

import com.gojek.domain.entities.CityWeather
import com.gojek.domain.entities.request.WeatherRequest

import io.reactivex.Observable

interface WeatherDataRepo {

    fun getWeatherUpdate(request: WeatherRequest?): Observable<CityWeather>
}