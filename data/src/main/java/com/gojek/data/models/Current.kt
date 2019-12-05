package com.gojek.data.models

import com.google.gson.annotations.SerializedName


data class Current(

	@field:SerializedName("feelslike_c")
	var feelslikeC: Double? = null,

	@field:SerializedName("uv")
	var uv: Double? = null,

	@field:SerializedName("last_updated")
	var lastUpdated: String? = null,

	@field:SerializedName("feelslike_f")
	var feelslikeF: Double? = null,

	@field:SerializedName("wind_degree")
	var windDegree: Int? = null,

	@field:SerializedName("last_updated_epoch")
	var lastUpdatedEpoch: Int? = null,

	@field:SerializedName("is_day")
	var isDay: Int? = null,

	@field:SerializedName("precip_in")
	var precipIn: Double? = null,

	@field:SerializedName("wind_dir")
	var windDir: String? = null,

	@field:SerializedName("gust_mph")
	var gustMph: Double? = null,

	@field:SerializedName("temp_c")
	var tempC: Double? = null,

	@field:SerializedName("pressure_in")
	var pressureIn: Double? = null,

	@field:SerializedName("gust_kph")
	var gustKph: Double? = null,

	@field:SerializedName("temp_f")
	var tempF: Double? = null,

	@field:SerializedName("precip_mm")
	var precipMm: Double? = null,

	@field:SerializedName("cloud")
	var cloud: Int? = null,

	@field:SerializedName("wind_kph")
	var windKph: Double? = null,

	@field:SerializedName("condition")
	var condition: Condition? = null,

	@field:SerializedName("wind_mph")
	var windMph: Double? = null,

	@field:SerializedName("vis_km")
	var visKm: Double? = null,

	@field:SerializedName("humidity")
	var humidity: Int? = null,

	@field:SerializedName("pressure_mb")
	var pressureMb: Double? = null,

	@field:SerializedName("vis_miles")
	var visMiles: Double? = null
)