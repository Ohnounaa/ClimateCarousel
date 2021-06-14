package com.ohnouna.climatecarouselapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MultiDayWeatherForecastFragment: Fragment() {


    lateinit var fragmentLayout: View

    private val weatherDataViewModel:WeatherDataViewModel by lazy {
        ViewModelProvider(requireActivity()).get(WeatherDataViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentLayout = inflater.inflate(R.layout.main_fragment, container, false)

        return fragmentLayout
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherDataViewModel.getWeather().observe(
            viewLifecycleOwner,
            { weatherInfo ->
                //TODO maybe the scope should be smaller
               GlobalScope.launch {add()}
            },
        )
    }


    private fun add() {

        weatherDataViewModel.addWeatherToDatabase()
    }
}