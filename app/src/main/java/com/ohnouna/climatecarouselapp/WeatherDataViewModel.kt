package com.ohnouna.climatecarouselapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo
import kotlinx.coroutines.*

class WeatherDataViewModel: ViewModel() {


    private val repository = WeatherDataRepository.retrieve()

    private val weather: MutableLiveData<List<DailyWeatherInfo>> = loadWeather()
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getWeather():LiveData<List<DailyWeatherInfo>> { return weather }
    private fun loadWeather(): MutableLiveData<List<DailyWeatherInfo>> { return repository.getWeatherDataFromAPI() }
    fun addWeatherToDatabase() {
        //cannot write to database from UI thread so must handle this operation via coroutine
      uiScope.launch(Dispatchers.IO) {
          for(item in weather.value!!) {
              repository.insertWeatherInfo(item)
          }
      }
    }

}