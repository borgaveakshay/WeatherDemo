package com.gojek.domain.repository

import com.gojek.domain.entities.CityWeather
import com.gojek.domain.entities.request.WeatherRequest
import io.reactivex.Observable

interface WeatherRepository {

    fun getWeatherUpdate(request: WeatherRequest?): Observable<CityWeather>
}