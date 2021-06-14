package com.ohnouna.climatecarouselapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo
import com.ohnouna.climatecarouselapp.databinding.ViewHolderBinding
import kotlinx.android.synthetic.main.main_fragment.view.*

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
        fragmentLayout.daily_weather_data_collection.apply{
            run {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                adapter = WeatherAdapter(weatherDataViewModel.getWeather().value)
            }

        }

        return fragmentLayout;
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherDataViewModel.getWeather().observe(
            viewLifecycleOwner,
            { writeWeatherDataToDatabase() }
        )
    }
    private fun writeWeatherDataToDatabase() { weatherDataViewModel.addWeatherToDatabase() }

    private inner class WeatherAdapter(private val weatherList: List<DailyWeatherInfo>?) : RecyclerView.Adapter<WeatherViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater,
                R.layout.view_holder,
                parent,
                false
            )
            return WeatherViewHolder(binding as ViewHolderBinding)
        }

        override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
            val dailyWeather = weatherList?.get(position)
            if (dailyWeather != null) {
                holder.bind(dailyWeather)
            }
        }

        override fun getItemCount(): Int {
           return weatherList?.size?:0
        }

    }


   private inner class WeatherViewHolder(private val binding: ViewHolderBinding):
       RecyclerView.ViewHolder(binding.root) {

           init{
               binding.viewModel = weatherDataViewModel
           }

       fun bind(weatherDay: DailyWeatherInfo) {
           binding.apply {
               viewModel?.dailyWeather = weatherDay
               executePendingBindings()
           }
       }

   }
}