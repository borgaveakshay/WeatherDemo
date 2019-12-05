package com.gojek.domain.entities.request

data class WeatherRequest(
    val authToken: String,
    val cityName: String,
    val numberOFDays: Int
)