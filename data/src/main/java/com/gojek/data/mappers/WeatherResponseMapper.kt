package com.gojek.data.mappers

import android.util.Log
import com.gojek.data.models.WeatherUpdate
import com.gojek.domain.entities.CityWeather
import com.gojek.domain.entities.Temperature
import com.gojek.data.utils.Utils
import com.gojek.domain.utils.Mapper
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class WeatherResponseMapper : Mapper<WeatherUpdate, CityWeather>() {

    override fun mapFrom(from: WeatherUpdate): CityWeather {

        val cityWeather = CityWeather()

        val dateFormatter = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())

        val temperatures: MutableList<Temperature> = mutableListOf()

        val calender = Calendar.getInstance()

        from.location?.let { location ->
            location.name?.let { cityName ->
                cityWeather.cityName = cityName
            }
        }

        from.forecast?.let { forecast ->

            forecast.forecastday?.let { list ->

                list.forEach { forecastList ->

                    forecastList?.let { item ->

                        item.day?.let { day ->

                            val temperature = Temperature()
                            temperature.celcious = day.avgtempC
                            try {
                                val date = dateFormatter.parse(item.date)
                                calender.time = date
                                temperature.day = Utils.parseDay(calender.get(Calendar.DAY_OF_WEEK))
                            } catch (e: Exception) {
                                Log.i("Mapper", "Date parse failed: ${e.message}")
                            }
                            temperatures.add(temperature)
                        }

                    }
                }

            }

        }
        cityWeather.lastFiveDaysTemp = temperatures

        return cityWeather
    }
}