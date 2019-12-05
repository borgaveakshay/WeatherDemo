package com.gojek.data.utils

import com.gojek.data.models.*

object TestUtils {

    fun getWeatherReport(): WeatherUpdate {


        val current = Current()
        val location = Location(name = "Pune")
        val forecast = Forecast()

        val forcastItem1 = ForecastdayItem(date = "2019-06-11", day = Day(avgtempC = 11.00))
        val forcastItem2 = ForecastdayItem(date = "2019-06-12", day = Day(avgtempC = 12.00))
        val forcastItem3 = ForecastdayItem(date = "2019-06-13", day = Day(avgtempC = 13.00))
        val forcastItem4 = ForecastdayItem(date = "2019-06-14", day = Day(avgtempC = 14.00))
        val forcastItem5 = ForecastdayItem(date = "2019-06-15", day = Day(avgtempC = 15.00))


        val list: MutableList<ForecastdayItem> = mutableListOf()
        list.add(forcastItem1)
        list.add(forcastItem2)
        list.add(forcastItem3)
        list.add(forcastItem4)
        list.add(forcastItem5)

        forecast.forecastday = list


        return WeatherUpdate(current, location, forecast)

    }
}