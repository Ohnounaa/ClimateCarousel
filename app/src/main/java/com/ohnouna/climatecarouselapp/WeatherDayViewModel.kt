package com.ohnouna.climatecarouselapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo

class WeatherDayViewModel: ViewModel() {

    private val repository = WeatherDataRepository.retrieve()
    private val weatherDateLiveData = MutableLiveData<Int>()


    var weatherLiveData: LiveData<DailyWeatherInfo> = Transformations.switchMap(weatherDateLiveData) { wd ->
        repository.getSingleDayWeatherInfo(wd)
    }

    fun loadWeatherForSingleDay(dt: Int){
        weatherDateLiveData.value = dt
    }

}