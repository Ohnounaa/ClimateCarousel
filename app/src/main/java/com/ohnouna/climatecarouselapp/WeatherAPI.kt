package com.ohnouna.climatecarouselapp

import com.ohnouna.climatecarouselapp.data.WeatherData
import retrofit2.Call
import retrofit2.http.GET

interface WeatherAPI {
    @GET("data/2.5/forecast/daily?q=New%20York&cnt=16&mode=json&units=metric&appid=648a3aac37935e5b45e09727df728ac2")
    fun retrieveWeatherData() : Call<WeatherData>
}
