package com.ohnouna.climatecarouselapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo

class WeatherDataViewModel: ViewModel() {

    private val weather: MutableLiveData<List<DailyWeatherInfo>> by lazy {
        MutableLiveData<List<DailyWeatherInfo>>().also{
            loadWeather()
        }
    }


    val repo = WeatherDataRepository.retrieve()
    val d: List<DailyWeatherInfo> = repo.getAllWeatherInfo()

    fun getWeather():LiveData<List<DailyWeatherInfo>> {
        return weather
    }


    private fun loadWeather() {
        //TODO add coroutine
        WeatherDataRetriever().retrieveWeatherData()
        addWeatherToRoomDatabase()
    }

    private fun addWeatherToRoomDatabase() {

    }

}