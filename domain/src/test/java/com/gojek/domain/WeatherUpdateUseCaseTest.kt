package com.gojek.domain

import com.gojek.domain.entities.request.WeatherRequest
import com.gojek.domain.repository.WeatherRepository
import com.gojek.domain.usecases.WeatherUpdateUseCase
import com.gojek.domain.utils.TestTransformer
import com.gojek.domain.utils.TestUtils
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class WeatherUpdateUseCaseTest {


    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weatherRequest: WeatherRequest


    @Before
    fun before() {

        weatherRequest = WeatherRequest(authToken = "token", cityName = "Pune", numberOFDays = 5)
        weatherRepository = Mockito.mock(WeatherRepository::class.java)
    }


    @Test
    fun test() {

        Mockito.`when`(weatherRepository.getWeatherUpdate(weatherRequest))
            .thenReturn(Observable.just(TestUtils.getWeatherUpdate()))

        val useCase = WeatherUpdateUseCase(weatherRepository, TestTransformer())

        useCase.observable(request = weatherRequest).test()
            .assertValue { result ->
                result.lastFiveDaysTemp.size == 5
                result.lastFiveDaysTemp[0].celcious == 11.0
            }.assertComplete()
    }
}