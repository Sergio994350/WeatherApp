package com.sergio994350.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }


    private fun initViews(){
        main_city_name_tv.text = "Moscow"
        main_date_tv.text = "January, 06"
        main_weather_condition_description.text = "Clear"
        main_weather_condition_icon.setImageResource(R.drawable.ic_sun)
        main_temp.text = "25\u00B0"
        main_temp_min_tv.text = "19\u00B0"
        main_temp_max_tv.text = "27\u00B0"
        main_weather_image.setImageResource(R.mipmap.sun_cloud_1x)
        main_pressure_mu_tv.text = "1023 hPa"
        main_humidity_mu_tv.text = "87 %"
        main_wind_speed_mu_tv.text = "5 m/s"
        main_sunrise_mu_tv.text = "05:50"
        main_sunset_mu_tv.text = "21:30"
    }
}