package com.ohnouna.climatecarouselapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo
import com.ohnouna.climatecarouselapp.data.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherDataRetriever {

    fun retrieveWeatherData(): MutableLiveData<List<DailyWeatherInfo>> {
        var responseLiveDataDaily: MutableLiveData<List<DailyWeatherInfo>> = MutableLiveData()

        val url = "https://api.openweathermap.org/"
        val retrofit: Retrofit = Retrofit.
        Builder().
        baseUrl(url).
        addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: WeatherAPI = retrofit.create(WeatherAPI::class.java)
        val retriever = api.retrieveWeatherData()

        retriever.enqueue(object: Callback<WeatherData> {
            override fun onResponse(
                call: Call<WeatherData>,
                response: Response<WeatherData>
            ) {
                response.isSuccessful.let {
                    val resp = WeatherData(response.body()!!.city,
                        response.body()!!.cnt,
                        response.body()!!.cod,
                        response.body()!!.list,
                        response.body()!!.message
                    )
                    responseLiveDataDaily.value = resp.list
                }
            }
            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.d("ERROR", t.message.toString())
            }
        })
        return responseLiveDataDaily
    }

}