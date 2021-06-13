package com.ohnouna.climatecarouselapp

import android.app.Application

class WeatherRepositoryInitializer: Application() {
    override fun onCreate() {
        super.onCreate()
        WeatherDataRepository.initialize(this)
    }
}