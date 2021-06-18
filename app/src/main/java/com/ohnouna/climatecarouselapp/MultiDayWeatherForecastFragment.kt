package com.ohnouna.climatecarouselapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ohnouna.climatecarouselapp.data.DailyWeatherInfo
import com.ohnouna.climatecarouselapp.databinding.WeatherViewHolderBinding
import kotlinx.android.synthetic.main.city_weather_detail_fragment.view.*

class MultiDayWeatherForecastFragment: Fragment() {


    lateinit var fragmentLayout: View
    private val weatherViewModel:WeatherViewModel by lazy {
        ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding:ViewDataBinding = DataBindingUtil.inflate(inflater,
            R.layout.city_weather_detail_fragment,
            container,
            false)
        fragmentLayout = binding.root
        return fragmentLayout;
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val cityName = arguments?.getString("CITY") as String

            fragmentLayout.findViewById<TextView>(R.id.detail_view_title).text =  "16 DAY WEATHER FORECAST FOR" + cityName
        weatherViewModel.getWeather(cityName)?.observe(
            viewLifecycleOwner,
            { weatherInfo ->
                writeWeatherDataToDatabase()
                fragmentLayout.daily_weather_data_collection.apply {
                    run {
                        layoutManager = ScalingLinearLayoutManager(context)
                        adapter = WeatherAdapter(weatherInfo)
                        addItemDecoration(CustomItemDecoration())
                        PagerSnapHelper().attachToRecyclerView(this)
                    }
                }
            }
        )
    }
    private fun writeWeatherDataToDatabase() { weatherViewModel.addWeatherToDatabase() }

    private inner class WeatherAdapter(private val weatherList: List<DailyWeatherInfo>?) : RecyclerView.Adapter<WeatherViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                WeatherViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater,
                R.layout.weather_view_holder,
                parent,
                false
            )
            return WeatherViewHolder(binding as WeatherViewHolderBinding)
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

   private inner class WeatherViewHolder(private val binding: WeatherViewHolderBinding): RecyclerView.ViewHolder(binding.root) {

       init{ binding.weatherDataViewModel = WeatherViewModel() }

       fun bind(weatherDay: DailyWeatherInfo) {
           binding.apply {
               weatherDataViewModel?.w = weatherDay
               executePendingBindings()
           }
       }
   }

    companion object {
        fun newInstance(city: String?) :MultiDayWeatherForecastFragment{
            val args = Bundle().apply{ putString("CITY", city) }
            return MultiDayWeatherForecastFragment().apply {  arguments = args }
        }
    }
}