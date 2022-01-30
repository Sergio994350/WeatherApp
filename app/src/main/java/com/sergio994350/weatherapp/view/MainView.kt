package com.sergio994350.weatherapp.view

import com.sergio994350.weatherapp.business.model.DailyWeatherModel
import com.sergio994350.weatherapp.business.model.HourlyWeatherModel
import com.sergio994350.weatherapp.business.model.WeatherData
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {

    @AddToEndSingle
    fun displayLocation(data: String)

    @AddToEndSingle
    fun displayCurrentData(data: WeatherData)

    @AddToEndSingle
    fun displayHourlyData(data: List<HourlyWeatherModel>)

    @AddToEndSingle
    fun displayDailyData(data: List<DailyWeatherModel>)

    @AddToEndSingle
    fun displayError(error: Throwable)

    @AddToEndSingle
    fun setLoading(flag: Boolean)
}