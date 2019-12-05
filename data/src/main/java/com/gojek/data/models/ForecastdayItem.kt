package com.gojek.data.models

import com.google.gson.annotations.SerializedName


data class ForecastdayItem(

	@field:SerializedName("date")
	var date: String? = null,

	@field:SerializedName("astro")
	var astro: Astro? = null,

	@field:SerializedName("date_epoch")
	var dateEpoch: Int? = null,

	@field:SerializedName("day")
	var day: Day? = null
)