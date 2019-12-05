package com.gojek.data.api

import com.gojek.data.models.WeatherUpdate
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("forecast.json")
    fun getWeatherUpdate(@Query("key") authKey: String, @Query("q") city: String, @Query("days") numberOfDays: Int): Observable<WeatherUpdate>
}