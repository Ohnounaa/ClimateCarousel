package com.ohnouna.climatecarouselapp.data

data class WeatherResponse(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)