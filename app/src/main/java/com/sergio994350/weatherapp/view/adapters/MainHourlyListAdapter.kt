package com.sergio994350.weatherapp.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textview.MaterialTextView
import com.sergio994350.weatherapp.R
import com.sergio994350.weatherapp.business.model.HourlyWeatherModel

class MainHourlyListAdapter : BaseAdapter<HourlyWeatherModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_hourly, parent, false)
        return HourlyViewHolder(view)
    }

    @SuppressLint("NonConstantResourceId")
    inner class HourlyViewHolder(view: View) : BaseViewHolder(view) {

        @BindView(R.id.item_hourly_time_tv)
        lateinit var time: MaterialTextView

        @BindView(R.id.item_hourly_temp_tv)
        lateinit var temperature: MaterialTextView

        @BindView(R.id.item_hourly_pop_tv)
        lateinit var popRate: MaterialTextView

        @BindView(R.id.item_hourly_weather_condition_icon)
        lateinit var icon: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindView(position: Int) {
            time.text = "14:00"
            temperature.text = "16\u00B0"
            popRate.text = "99%"
            icon.setImageResource(R.drawable.ic_sunny_24)
        }
    }

}