package com.gojek.domain.usecases

import com.gojek.domain.entities.CityWeather
import com.gojek.domain.entities.request.WeatherRequest
import com.gojek.domain.repository.WeatherRepository
import com.gojek.domain.utils.Transformer
import io.reactivex.Observable

class WeatherUpdateUseCase(
    private val weatherRepository: WeatherRepository,
    transformer: Transformer<CityWeather>
) : BaseUseCase<WeatherRequest, CityWeather>(transformer) {

    override fun createObservable(request: WeatherRequest?): Observable<CityWeather> {
        return weatherRepository.getWeatherUpdate(request)
    }
}