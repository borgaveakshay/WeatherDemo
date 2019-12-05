package com.gojek.data.datastores

import com.gojek.data.api.WeatherAPI
import com.gojek.data.mappers.WeatherResponseMapper
import com.gojek.domain.entities.CityWeather
import com.gojek.domain.datastore.WeatherDataRepo
import com.gojek.domain.entities.request.WeatherRequest
import io.reactivex.Observable
import javax.inject.Inject

class WeatherDataStore(
    private val api: WeatherAPI,
    private val mapper: WeatherResponseMapper
) : WeatherDataRepo {


    override fun getWeatherUpdate(request: WeatherRequest?): Observable<CityWeather> {
        return api.getWeatherUpdate(request!!.authToken, request.cityName, request.numberOFDays)
            .map {
                mapper.mapFrom(it)
            }
    }
}