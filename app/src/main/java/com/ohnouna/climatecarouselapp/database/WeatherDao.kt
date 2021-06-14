package com.ohnouna.climatecarouselapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo
import com.ohnouna.climatecarouselapp.data.FeelsLike
import com.ohnouna.climatecarouselapp.data.Temp
import com.ohnouna.climatecarouselapp.data.WeatherResponse

@Dao
interface WeatherDao{
    @Query("SELECT * FROM DailyWeatherInfo")
    fun getAllWeatherInfo(): LiveData<List<DailyWeatherInfo>>

    @Query("SELECT * FROM DailyWeatherInfo WHERE dt=(:dt)")
    fun getSingleDayWeatherInfo(dt:Int):LiveData<DailyWeatherInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherDay(dwi:DailyWeatherInfo)

    }
