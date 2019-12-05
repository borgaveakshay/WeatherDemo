package com.gojek.waetherdemo.views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gojek.domain.usecases.WeatherUpdateUseCase
import com.gojek.waetherdemo.R
import com.gojek.waetherdemo.databinding.ActivityMainBinding
import com.gojek.waetherdemo.di.component.DaggerDataComponent
import com.gojek.waetherdemo.di.module.ContextModule
import com.gojek.waetherdemo.di.module.NetworkModule
import com.gojek.waetherdemo.utils.Constants
import com.gojek.waetherdemo.utils.LocationUpdateManager
import com.gojek.waetherdemo.utils.Utils
import com.gojek.waetherdemo.views.adapters.TemperaturesAdapter
import com.gojek.waetherdemo.views.viewmodels.WeatherUpdateViewModel
import com.gojek.waetherdemo.views.viewmodels.WeatherViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*
import java.security.Permission
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var mWeatherViewModel: WeatherUpdateViewModel

    private lateinit var mActivityMainBinding: ActivityMainBinding

    @Inject
    lateinit var mWeatherUpdateUseCase: WeatherUpdateUseCase

    @Inject
    lateinit var mLocationUpdateManager: LocationUpdateManager

    private lateinit var mTemperaturesAdapter: TemperaturesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerDataComponent.builder()
            .networkModule(NetworkModule())
            .contextModule(ContextModule(this))
            .build()
            .inject(this)

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mWeatherViewModel = ViewModelProviders.of(this, WeatherViewModelFactory(mWeatherUpdateUseCase))
            .get(WeatherUpdateViewModel::class.java)



        mActivityMainBinding.lifecycleOwner = this


        initializeAdapter()
        checkLocationPermission()
        setRetryListener()
    }


    private fun setRetryListener() {

        retry.setOnClickListener {

            checkLocationPermission()
        }
    }


    private fun checkLocationPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
            if (checkPermissionFlag(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    Constants.LOCATION_PERMISSION
                )
            )
                getWeatherUpdate()
            else
                getWeatherUpdate()
    }

    private fun initializeAdapter() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mTemperaturesAdapter = TemperaturesAdapter()
        lastFiveDaysWeather.layoutManager = layoutManager
        lastFiveDaysWeather.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        lastFiveDaysWeather.adapter = mTemperaturesAdapter
        lastFiveDaysWeather.isNestedScrollingEnabled = false
    }

    private fun getWeatherUpdate() {
        mLocationUpdateManager.getCityName().observe(this, Observer { city ->

            showProgressBar()
            city?.let {

                mWeatherViewModel.getWeatherUpdate(it).observe(this@MainActivity, Observer {
                    hideProgressBar()

                    if (mWeatherViewModel.isError) {
                        showErrorPage()
                    } else {
                        showMainLayout()
                    }

                    it?.let { weather ->

                        weather.lastFiveDaysTemp.let { list ->
                            mActivityMainBinding.weather = weather
                            mActivityMainBinding.temperature = list[0]
                            mTemperaturesAdapter.updateItems(list.subList(1, 4).reversed().toMutableList())
                            Utils.animateUp(this@MainActivity, bottom)
                        }

                    }

                })

            }

        })


    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == Constants.LOCATION_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getWeatherUpdate()

            } else {
                Toast.makeText(this@MainActivity, "Location permission not granted!!", Toast.LENGTH_LONG).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
