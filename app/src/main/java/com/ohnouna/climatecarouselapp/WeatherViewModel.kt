package com.ohnouna.climatecarouselapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat

@BindingAdapter("bind:imageUrl")
fun loadImage(iv: ImageView, url: String) {
    Picasso
        .with(iv.context)
        .load(url).centerCrop().resize(200, 200).into(iv)
}

class WeatherViewModel: ViewModel() {

    var w: DailyWeatherInfo?  = null
    var city = ""
    private val repository = WeatherDataRepository.retrieve()
    private var weather: MutableLiveData<List<DailyWeatherInfo>>? = null
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getWeather(cityName: String): LiveData<List<DailyWeatherInfo>>? {
        weather = loadWeather(cityName)
        return weather;
    }

    private fun loadWeather(city: String): MutableLiveData<List<DailyWeatherInfo>> {
        return repository.getWeatherDataFromAPI(city)
    }
    fun addWeatherToDatabase() {
        //cannot write to database from UI thread so must handle this operation via coroutine
        uiScope.launch(Dispatchers.IO) {
            for(item in weather?.value!!) {
                repository.insertWeatherInfo(item)
            }
        }
    }

    fun convertUnixToGregorianDate(unixDate: Int):String {
        val calendarDate = Date(unixDate.toLong() * 1000)
        val dateFormatter = SimpleDateFormat("EEEE, MMMM d, yyyy")
        val d = dateFormatter.format(calendarDate)
        return d.toString()
    }
}