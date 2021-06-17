package com.ohnouna.climatecarouselapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityListFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(cityListFragment == null) {
            val f = CityListFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container,f).commit()
        }

        val cityViewModel:CityViewModel by lazy {
            ViewModelProvider(this).get(CityViewModel::class.java)
        }

        cityViewModel.selectedCity.observe(
            this,
            { selectedCity ->
               val f = MultiDayWeatherForecastFragment.newInstance(selectedCity)
               supportFragmentManager
                   .beginTransaction()
                   .replace(R.id.fragment_container, f)
                   .addToBackStack("Add")
                   .commit()
            }
        )
    }
}