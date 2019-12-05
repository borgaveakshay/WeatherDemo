package com.gojek.data.models

import com.google.gson.annotations.SerializedName


data class WeatherUpdate(

	@field:SerializedName("current")
	var current: Current? = null,

	@field:SerializedName("location")
	var location: Location? = null,

	@field:SerializedName("forecast")
	var forecast: Forecast? = null
)