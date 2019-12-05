package com.gojek.domain.entities


import androidx.room.Entity

@Entity
data class Temperature(
    var day: String = "",
    var celcious: Double? = 0.0
)