package com.ohnouna.climatecarouselapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo

class WeatherDataViewModel: ViewModel() {

    private val repo = WeatherDataRepository.retrieve()

    private val weather: MutableLiveData<List<DailyWeatherInfo>> = loadWeather()

    fun getWeather():LiveData<List<DailyWeatherInfo>> {
        return weather
    }


    private fun loadWeather(): MutableLiveData<List<DailyWeatherInfo>> {


        return repo.getWeatherDataFromAPI()
    }
}