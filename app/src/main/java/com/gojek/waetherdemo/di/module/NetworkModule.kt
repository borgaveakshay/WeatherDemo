package com.gojek.waetherdemo.di.module

import com.gojek.data.api.WeatherAPI
import com.gojek.data.datastores.WeatherDataStore
import com.gojek.data.mappers.WeatherResponseMapper
import com.gojek.data.repositories.WeatherRepositoryImpl
import com.gojek.domain.datastore.WeatherDataRepo
import com.gojek.domain.repository.WeatherRepository
import com.gojek.domain.usecases.WeatherUpdateUseCase
import com.gojek.waetherdemo.BuildConfig.BASE_URL
import com.gojek.waetherdemo.utils.AsyncTransformer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {


    @Provides
    @Singleton
     fun getOkHttpClient(): OkHttpClient {

        return OkHttpClient
            .Builder()
            .addInterceptor(getHttpLogger()).build()
    }

    @Provides
    @Singleton
     fun getHttpLogger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getOkHttpClient())
            .build()

    }


    @Singleton
    @Provides
    fun getAPI(): WeatherAPI {

        return getRetrofit().create(WeatherAPI::class.java)
    }

    @Singleton
    @Provides
    fun getDataStore(): WeatherDataRepo {

        return WeatherDataStore(getAPI(), WeatherResponseMapper())
    }

    @Singleton
    @Provides
    fun getWeatherRepository(): WeatherRepository {

        return WeatherRepositoryImpl(getDataStore())
    }


    @Singleton
    @Provides
    fun getWeatherUpdateUseCase(): WeatherUpdateUseCase {

        return WeatherUpdateUseCase(getWeatherRepository(), AsyncTransformer())
    }


}