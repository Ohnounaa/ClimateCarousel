package com.ohnouna.climatecarouselapp

import com.ohnouna.climatecarouselapp.data.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/forecast/daily?cnt=16&mode=json&units=metric&appid=648a3aac37935e5b45e09727df728ac2")
    fun retrieveWeatherDataForNewYork(@Query("q")city: String): Call<WeatherData>
}
