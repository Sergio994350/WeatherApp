package com.sergio994350.weatherapp.presenters

import com.sergio994350.weatherapp.view.MainView

class MainPresenter : BasePresenter<MainView>() {
    // TODO тут будет переменная репозитория


    override fun enable() {

    }

    fun refresh(lat: String, lon: String) {
        viewState.setLoading(true)
        // TODO : будет обращение к репозитории
    }

}