package com.ohnouna.climatecarouselapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo
import com.ohnouna.climatecarouselapp.database.WeatherDatabase

class WeatherDataRepository private constructor(context: Context){

    fun getWeatherDataFromAPI(city: String): MutableLiveData<List<DailyWeatherInfo>> {
        return WeatherDataRetriever().retrieveWeatherData(city);
    }

    private val database: WeatherDatabase = Room.databaseBuilder(
        context.applicationContext,
        WeatherDatabase::class.java,
        "weather-database").fallbackToDestructiveMigration().build()

    private val weatherDao = database.dailyWeatherDao()

    fun getAllWeatherInfo(): LiveData<List<DailyWeatherInfo>> = weatherDao.getAllWeatherInfo();
    fun getSingleDayWeatherInfo(dt:Int): LiveData<DailyWeatherInfo> = weatherDao.getSingleDayWeatherInfo(dt);

    fun insertWeatherInfo(dwi:DailyWeatherInfo) = weatherDao.insertWeatherDay(dwi)


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