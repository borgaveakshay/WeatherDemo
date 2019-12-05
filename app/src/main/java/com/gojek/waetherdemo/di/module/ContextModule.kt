package com.gojek.waetherdemo.di.module

import android.content.Context
import com.gojek.waetherdemo.utils.LocationUpdateManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {


    @Provides
    @Singleton
    fun getContext() = context


    @Singleton
    @Provides
    fun getLocationManager(): LocationUpdateManager {

        return LocationUpdateManager(getContext())
    }
}