package com.ohnouna.climatecarouselapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo

@Database(entities = [DailyWeatherInfo:: class], version=1, exportSchema = false)
@TypeConverters(WeatherTypeConverters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun dailyWeatherDao() :WeatherDao
}