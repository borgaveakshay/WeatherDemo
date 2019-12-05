package com.gojek.data.models

import com.google.gson.annotations.SerializedName


data class Forecast(

	@field:SerializedName("forecastday")
	var forecastday: List<ForecastdayItem?>? = null
)