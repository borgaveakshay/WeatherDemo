package com.gojek.domain.utils

import com.gojek.domain.entities.CityWeather
import com.gojek.domain.entities.Temperature

object TestUtils {

    fun getWeatherUpdate(): CityWeather {

        val cityWeather = CityWeather()

        cityWeather.cityName = "Pune"

        val list: MutableList<Temperature> = mutableListOf()


        val temperature1 = Temperature("Monday", 11.0)
        val temperature2 = Temperature("Tuesday", 15.0)
        val temperature3 = Temperature("Wednesday", 17.0)
        val temperature4 = Temperature("Thursday", 18.0)
        val temperature5 = Temperature("Friday", 19.0)

        list.add(temperature1)
        list.add(temperature2)
        list.add(temperature3)
        list.add(temperature4)
        list.add(temperature5)
        cityWeather.lastFiveDaysTemp = list
        return cityWeather
    }
}