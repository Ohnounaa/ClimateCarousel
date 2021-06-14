package com.ohnouna.climatecarouselapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

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

        val binding:ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        fragmentLayout = binding.root

        return fragmentLayout;
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherDataViewModel.getWeather().observe(
            viewLifecycleOwner,
            { weatherInfo ->
                  writeWeatherDataToDatabase()
            },
        )
    }
    private fun writeWeatherDataToDatabase() { weatherDataViewModel.addWeatherToDatabase() }

}