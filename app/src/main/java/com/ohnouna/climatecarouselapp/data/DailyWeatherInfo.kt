package com.ohnouna.climatecarouselapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ohnouna.climatecarouselapp.database.WeatherTypeConverters
import java.util.*
import java.util.UUID.randomUUID

@Entity
data class DailyWeatherInfo (
    val clouds: Int,
    val deg: Int,
    @PrimaryKey val dt: Int,
    @TypeConverters(WeatherTypeConverters::class)
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Float,
    val pressure: Int,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    @TypeConverters(WeatherTypeConverters::class)
    val temp: Temp,
    @TypeConverters(WeatherTypeConverters::class)
    val weather: List<WeatherResponse>
)