package com.gojek.data.models

import com.google.gson.annotations.SerializedName


data class Location(

	@field:SerializedName("localtime")
	var localtime: String? = null,

	@field:SerializedName("country")
	var country: String? = null,

	@field:SerializedName("localtime_epoch")
	var localtimeEpoch: Int? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("lon")
	var lon: Double? = null,

	@field:SerializedName("region")
	var region: String? = null,

	@field:SerializedName("lat")
	var lat: Double? = null,

	@field:SerializedName("tz_id")
	var tzId: String? = null
)