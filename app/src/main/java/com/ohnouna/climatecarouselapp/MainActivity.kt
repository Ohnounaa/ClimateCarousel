package com.ohnouna.climatecarouselapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val summaryFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(summaryFragment == null) {
            val f = MultiDayWeatherForecastFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, f).commit()
        }


    }
}