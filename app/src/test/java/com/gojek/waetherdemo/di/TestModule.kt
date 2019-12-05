package com.gojek.waetherdemo.di

import com.gojek.data.api.WeatherAPI
import com.gojek.data.datastores.WeatherDataStore
import com.gojek.data.mappers.WeatherResponseMapper
import com.gojek.data.repositories.WeatherRepositoryImpl
import com.gojek.domain.datastore.WeatherDataRepo
import com.gojek.domain.repository.WeatherRepository
import com.gojek.domain.usecases.WeatherUpdateUseCase
import com.gojek.domain.utils.TestTransformer
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
class TestModule {


    @Provides
    @Singleton
    fun getAPI(): WeatherAPI = Mockito.mock(WeatherAPI::class.java)


    @Provides
    @Singleton
    fun getWeatherDataStore(): WeatherDataRepo = WeatherDataStore(getAPI(), WeatherResponseMapper())


    @Provides
    @Singleton
    fun getWeatherRepository(): WeatherRepository = WeatherRepositoryImpl(getWeatherDataStore())


    @Provides
    @Singleton
    fun getWeatherUseCase(): WeatherUpdateUseCase = WeatherUpdateUseCase(getWeatherRepository(), TestTransformer())
}