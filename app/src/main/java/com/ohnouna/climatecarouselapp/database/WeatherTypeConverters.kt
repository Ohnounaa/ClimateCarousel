package com.ohnouna.climatecarouselapp.database

import androidx.room.TypeConverter
import com.ohnouna.climatecarouselapp.data.*
import java.lang.StringBuilder
import java.util.*

class WeatherTypeConverters {

    @TypeConverter
    fun fromFeelsLikeToString(feelsLike: FeelsLike): String {
        val sb = StringBuilder()
        sb.append(feelsLike.day.toString() + ",")
        sb.append(feelsLike.eve.toString()  + ",")
        sb.append(feelsLike.morn.toString() + ",")
        sb.append(feelsLike.night.toString() + ",")
        return sb.toString()
    }

    @TypeConverter
    fun fromStringtoFeelsLike(feelsLikeString: String): FeelsLike? {
        val arrayList = feelsLikeString.split(",", ignoreCase = true, limit = 0)
        return FeelsLike(
            arrayList[0].toDouble(),
            arrayList[1].toDouble(),
            arrayList[2].toDouble(),
            arrayList[3].toDouble()
        );
    }

    @TypeConverter
    fun fromTempToString(temp: Temp): String {
        val sb = StringBuilder()
        sb.append(temp.day.toString() + ",")
        sb.append(temp.eve.toString()  + ",")
        sb.append(temp.max.toString() + ",")
        sb.append(temp.max.toString() + ",")
        sb.append(temp.morn.toString() + ",")
        sb.append(temp.night.toString() + ",")
        return sb.toString()
    }

    @TypeConverter
    fun fromStringToTemp(tempAsString: String): Temp? {
        var arrayList = tempAsString.split(",", ignoreCase = true, limit = 0)
        return Temp(
            arrayList[0].toDouble(),
            arrayList[1].toDouble(),
            arrayList[2].toDouble(),
            arrayList[3].toDouble(),
            arrayList[4].toDouble(),
            arrayList[5].toDouble()
        );
    }

    @TypeConverter
    fun fromUUIDToString(uuid: UUID): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun fromWeatherResponseToString(weatherResponse: kotlin.collections.MutableList<WeatherResponse>): String {
        val sb = StringBuilder()
        //only ever 1 entry in list
        val relevantResponse:WeatherResponse? = weatherResponse.first()
        sb.append(relevantResponse?.description + ", ")
        sb.append(relevantResponse?.icon + ", ")
        sb.append(relevantResponse?.id.toString() + ", ")
        sb.append(relevantResponse?.main + ", ")
        return sb.toString()
    }

    @TypeConverter
    fun fromStringToWeatherResponseList(weatherResponseAsString: String): MutableList<WeatherResponse?> {
        val arrayList = weatherResponseAsString.split(",", ignoreCase = true, limit = 0)
        val w = WeatherResponse(arrayList[0], arrayList[1], arrayList[2].toInt(), arrayList[3])
        val list:MutableList<WeatherResponse?> = mutableListOf()
        list.add(w)
        return list;

    }

    @TypeConverter
    fun fromStringToUUID(uuid: String): UUID? {
        return UUID.fromString(uuid)
    }
}