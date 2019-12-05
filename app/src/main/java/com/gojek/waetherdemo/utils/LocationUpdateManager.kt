package com.gojek.waetherdemo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class LocationUpdateManager(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)


    @SuppressLint("MissingPermission")
    fun getCityName(): LiveData<String> {

        val cityName: MutableLiveData<String> = MutableLiveData()
        fusedLocationClient.lastLocation.addOnSuccessListener {

            it?.let { location ->

                val geocoder = Geocoder(context, Locale.getDefault())
                val address: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                address.let { list ->
                    cityName.postValue(list[0].locality)
                }


            }

        }

        return cityName
    }
}