package com.gojek.waetherdemo.views.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gojek.domain.usecases.WeatherUpdateUseCase

class WeatherViewModelFactory(private val weatherUpdateUseCase: WeatherUpdateUseCase) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return WeatherUpdateViewModel(weatherUpdateUseCase) as T
    }
}