package com.ohnouna.climatecarouselapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityListFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(cityListFragment == null) {
            val f = CityListFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container,f).commit()
        }
    }


}