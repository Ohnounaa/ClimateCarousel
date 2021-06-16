package com.ohnouna.climatecarouselapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ohnouna.climatecarouselapp.databinding.CityListFragmentViewholderBinding
import kotlinx.android.synthetic.main.city_list_fragment.view.*

class CityListFragment: Fragment() {

    lateinit var fragmentLayout: View
    var listOfCities: ArrayList<String> = ArrayList()
    interface Callbacks{
        fun onCitySelected(city: String) {}
    }


    fun onCitySelected() {

    }

    private var callbacks: Callbacks? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        listOfCities.add("New York")
        listOfCities.add("Taipei")
        listOfCities.add("Rome")
        listOfCities.add("London")
        listOfCities.add("Paris")


        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater,
            R.layout.city_list_fragment,
            container,
            false)
        fragmentLayout = binding.root
        return fragmentLayout;
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentLayout.city_collection.apply {
            run {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = CityListAdapter(listOfCities, callbacks)
            }
        }
    }

    inner class CityListAdapter(private val cityList: ArrayList<String>, callbacks: Callbacks?):
        RecyclerView.Adapter<CityViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater,
                R.layout.city_list_fragment_viewholder,
                parent,
                false)

            return CityViewHolder(binding as CityListFragmentViewholderBinding)
        }

        override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
            val city = cityList[position]
            holder.bind(city)

        }

        override fun getItemCount(): Int {
           return listOfCities.size
        }
    }

    inner class CityViewHolder(private val binding: CityListFragmentViewholderBinding):
        RecyclerView.ViewHolder(binding.root) {

        init {binding.cityList?.listOfCities = listOfCities}

        fun bind(city:String){
            binding.apply {
                cityName.text = city
            }
        }
    }



}