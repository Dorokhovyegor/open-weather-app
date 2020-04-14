package com.dorokhov.openweatherapp.ui.main.weather_main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dorokhov.openweatherapp.R
import com.dorokhov.openweatherapp.api.responses.SingleItemWeather
import com.dorokhov.openweatherapp.model.WeatherModel
import com.dorokhov.openweatherapp.ui.main.weather_main.state.WeatherStateEvent
import com.dorokhov.openweatherapp.ui.main.weather_main.viewmodel.removeWeatherItem
import com.dorokhov.openweatherapp.ui.main.weather_main.viewmodel.setWeatherListData
import kotlinx.android.synthetic.main.fragment_weather.*
import java.util.ArrayList


class ViewWeatherFragment : BaseWeatherFragment(),  SwipeRefreshLayout.OnRefreshListener, WeatherListAdapter.Interaction {

    private lateinit var recyclerViewAdapter: WeatherListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_weather, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        swipe_refresh.setOnRefreshListener(this)
        initRecyclerView()
        subscribeObserver()

        ok_button.setOnClickListener {
            weatherViewModel.setStateEvent(WeatherStateEvent.GetWeatherInCurrentCircle(coordinates.text.toString()))
        }

    }

    private fun subscribeObserver() {
        weatherViewModel.dataState.observe(viewLifecycleOwner, Observer { dataState->
            if (dataState != null) {
                stateChangeListener.onDataStateChange(dataState)
                dataState.data?.let { data->
                    data.data?.let { event ->
                        event.getContentIfNotHandled()?.let { viewState->
                            weatherViewModel.setWeatherListData(viewState.weatherFields.weatherList)
                        }
                    }
                }
            }
        })

        weatherViewModel.viewState.observe(viewLifecycleOwner, Observer { viewState->
            Log.e("ViewWeather", viewState.toString())
            if (viewState != null) {
                if (viewState.weatherFields.weatherList.isEmpty()) {
                    empty_list.visibility = View.VISIBLE
                } else {
                    empty_list.visibility = View.GONE
                }

                recyclerViewAdapter.submitList(
                    list = viewState.weatherFields.weatherList
                )
            }
        })
    }

    private fun initRecyclerView() {
        weather_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@ViewWeatherFragment.context)
            recyclerViewAdapter = WeatherListAdapter(
                requestManager = requestManager,
                interaction = this@ViewWeatherFragment
            )
            adapter = recyclerViewAdapter
        }
    }

    override fun onRefresh() {
        weatherViewModel.setStateEvent(WeatherStateEvent.GetWeatherInCurrentCircle(coordinates.text.toString()))
        swipe_refresh.isRefreshing = false
    }

    override fun deleteItem(position: Int, item: WeatherModel) {
        weatherViewModel.removeWeatherItem(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // can leak memory
        weather_recycler_view.adapter = null
    }
}