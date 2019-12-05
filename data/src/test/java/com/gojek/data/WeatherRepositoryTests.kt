package com.gojek.data

import com.gojek.data.api.WeatherAPI
import com.gojek.data.datastores.WeatherDataStore
import com.gojek.data.mappers.WeatherResponseMapper
import com.gojek.data.repositories.WeatherRepositoryImpl
import com.gojek.data.utils.TestUtils
import com.gojek.domain.entities.request.WeatherRequest
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class WeatherRepositoryTests {

    private lateinit var mWeatherDataStore: WeatherDataStore
    private lateinit var mWeatherRequest: WeatherRequest
    private lateinit var mApi: WeatherAPI
    private lateinit var mMapper: WeatherResponseMapper

    @Before
    fun before() {

        mApi = Mockito.mock(WeatherAPI::class.java)
        mMapper = WeatherResponseMapper()
        mWeatherDataStore = WeatherDataStore(mApi, mMapper)
        mWeatherRequest = WeatherRequest(authToken = "Token", cityName = "Pune", numberOFDays = 5)


    }


    @Test
    fun test() {

        Mockito.`when`(mApi.getWeatherUpdate(mWeatherRequest.authToken, mWeatherRequest.cityName, mWeatherRequest.numberOFDays))
            .thenReturn(Observable.just(TestUtils.getWeatherReport()))

        val weatherRepo = WeatherRepositoryImpl(mWeatherDataStore)

        weatherRepo.getWeatherUpdate(mWeatherRequest).test()
            .assertValue { result ->
                result.lastFiveDaysTemp.size == 5
                result.cityName == "Pune"
                result.lastFiveDaysTemp[0].celcious == 11.0
            }.assertComplete()
    }


    @Test
    fun testDataStore() {

        Mockito.`when`(mApi.getWeatherUpdate(mWeatherRequest.authToken, mWeatherRequest.cityName, mWeatherRequest.numberOFDays))
            .thenReturn(Observable.just(TestUtils.getWeatherReport()))

        mWeatherDataStore.getWeatherUpdate(mWeatherRequest).test()
            .assertValue { result ->
                result.lastFiveDaysTemp.size == 5
                result.cityName == "Pune"
                result.lastFiveDaysTemp[0].celcious == 11.0
            }.assertComplete()
    }
}