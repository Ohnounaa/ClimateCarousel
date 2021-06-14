package com.ohnouna.climatecarouselapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherDataViewModel: ViewModel() {


    private val repository = WeatherDataRepository.retrieve()

    private val weather: MutableLiveData<List<DailyWeatherInfo>> = loadWeather()

    fun getWeather():LiveData<List<DailyWeatherInfo>> {
        return weather
    }

    private fun loadWeather(): MutableLiveData<List<DailyWeatherInfo>> {
           return repository.getWeatherDataFromAPI()
    }

    fun addWeatherToDatabase() {
            for(item in weather.value!!) {
                repository.insertWeatherInfo(item)
            }
    }
}