package com.ohnouna.climatecarouselapp

import android.content.Context
import androidx.room.Room
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo
import com.ohnouna.climatecarouselapp.data.FeelsLike
import com.ohnouna.climatecarouselapp.data.Temp
import com.ohnouna.climatecarouselapp.data.WeatherResponse
import com.ohnouna.climatecarouselapp.database.WeatherDatabase

class WeatherDataRepository private constructor(context: Context){

    private val database: WeatherDatabase = Room.databaseBuilder(
        context.applicationContext,
        WeatherDatabase::class.java,
        "weather-database").build()

    private val weatherDao = database.dailyWeatherDao()

    fun getAllWeatherInfo(): List<DailyWeatherInfo> = weatherDao.getAllWeatherInfo();
    fun getSingleDayWeatherInfo(dt:Int): DailyWeatherInfo = weatherDao.getSingleDayWeatherInfo(dt);
    fun insertWeatherInfo(dwi:DailyWeatherInfo
                          ) = weatherDao.insertWeatherDay(dwi)


    companion object{
        private var INSTANCE: WeatherDataRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = WeatherDataRepository(context)
            }
        }

        fun retrieve(): WeatherDataRepository {
            return INSTANCE?: throw IllegalStateException("Repository has not been initialized.")
        }

    }
}