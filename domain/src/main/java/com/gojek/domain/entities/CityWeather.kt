package com.gojek.domain.entities

import androidx.room.Entity

@Entity
data class CityWeather(
    var cityName: String = "",
    var lastFiveDaysTemp: List<Temperature> = emptyList()
)