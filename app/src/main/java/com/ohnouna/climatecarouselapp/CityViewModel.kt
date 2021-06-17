package com.ohnouna.climatecarouselapp


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import java.sql.Date
import java.text.SimpleDateFormat

class CityViewModel: ViewModel() {
    val selectedCity = MutableLiveData<String>()
    fun onCitySelected(city: String) {
        selectedCity.value = city
    }
}