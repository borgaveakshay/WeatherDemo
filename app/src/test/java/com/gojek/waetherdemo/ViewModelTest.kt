package com.gojek.waetherdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.gojek.domain.entities.CityWeather
import com.gojek.domain.entities.request.WeatherRequest
import com.gojek.domain.usecases.WeatherUpdateUseCase
import com.gojek.domain.utils.TestUtils
import com.gojek.waetherdemo.di.DaggerTestNetworkComponent
import com.gojek.waetherdemo.di.TestModule
import com.gojek.waetherdemo.views.viewmodels.WeatherUpdateViewModel
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import javax.inject.Inject


class ViewModelTest {


    @JvmField
    @Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @Inject
    lateinit var mWeatherUpdateUseCase: WeatherUpdateUseCase

//    @Inject
//    lateinit var mWeatherRepository: WeatherRepository
//
//    @Inject
//    lateinit var mWeatherDataStore: WeatherDataRepo
//
//    @Inject
//    lateinit var mWeatherAPI: WeatherAPI

    private lateinit var mWeatherUpdateViewModel: WeatherUpdateViewModel

    private lateinit var mWeatherRequest: WeatherRequest

    @Before
    fun before() {

        DaggerTestNetworkComponent
            .builder()
            .testModule(TestModule())
            .build()
            .inject(this)



    }


    @Test
    fun test() {

        mWeatherRequest = WeatherRequest("authToken", "Pune", 5)
        mWeatherUpdateViewModel = WeatherUpdateViewModel(mWeatherUpdateUseCase)

//        Mockito.`when`(
//            mWeatherAPI.getWeatherUpdate(
//                mWeatherRequest.authToken,
//                mWeatherRequest.cityName,
//                mWeatherRequest.numberOFDays
//            )
//        )
//            .thenReturn(Observable.just(com.gojek.data.utils.TestUtils.getWeatherReport()))
//
//        Mockito.`when`(mWeatherDataStore.getWeatherUpdate(mWeatherRequest))
//            .thenReturn(Observable.just(TestUtils.getWeatherUpdate()))
//
//        Mockito.`when`(mWeatherRepository.getWeatherUpdate(mWeatherRequest))
//            .thenReturn(Observable.just(TestUtils.getWeatherUpdate()))

//        Mockito.`when`(mWeatherUpdateUseCase.observable(mWeatherRequest))
//            .thenReturn(Observable.just(TestUtils.getWeatherUpdate()))

        Mockito.`when`(mWeatherUpdateUseCase.observable(mWeatherRequest)).thenReturn(Observable.just(TestUtils.getWeatherUpdate()))

        val result: LiveData<CityWeather> = mWeatherUpdateViewModel.getWeatherUpdate("Pune")

        Assert.assertTrue(result.value?.lastFiveDaysTemp?.size == 5)

    }
}