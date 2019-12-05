package com.gojek.waetherdemo.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gojek.domain.entities.CityWeather
import com.gojek.domain.entities.request.WeatherRequest
import com.gojek.domain.usecases.WeatherUpdateUseCase
import com.gojek.waetherdemo.BuildConfig.appId

class WeatherUpdateViewModel(private val mWeatherUpdateUseCase: WeatherUpdateUseCase) : BaseViewModel() {


    fun getWeatherUpdate(cityName: String): LiveData<CityWeather> {
        val weatherUpdateLiveData: MutableLiveData<CityWeather> = MutableLiveData()

        val request = WeatherRequest(appId, cityName, 5)


        isError = false
        mCompositeDisposable.add(mWeatherUpdateUseCase.observable(request).subscribe(
            { update ->

                isError = false
                update?.let {

                    weatherUpdateLiveData.postValue(it)
                }

            },
            {
                it?.let {
                    isError = true
                    weatherUpdateLiveData.postValue(null)
                }
            }
        ))

        return weatherUpdateLiveData
    }


}