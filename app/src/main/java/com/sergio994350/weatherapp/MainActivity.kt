package com.sergio994350.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sergio994350.weatherapp.view.adapters.MainDailyListAdapter
import com.sergio994350.weatherapp.view.adapters.MainHourlyListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.os.HandlerThread


const val GEO_LOCATION_REQUEST_COD_SUCCESS = 1
const val TAG = "GEO_TEST"

class MainActivity : AppCompatActivity() {

    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy { initLocationRequest() }
    private lateinit var mLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()

        initViews()

        main_hourly_list.apply {
            adapter = MainHourlyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        main_daily_list.apply {
            adapter = MainDailyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }

        // create Looper code
        val handlerThread = HandlerThread("MyHandlerThread")
        handlerThread.start()
        val looper = handlerThread.looper

        // request Location
        geoService.requestLocationUpdates(locationRequest, geoCallback, looper)

    }


    private fun initViews() {
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

    // ------------------- location code ------------------

    private fun initLocationRequest(): LocationRequest {
        val request = LocationRequest.create()
        return request.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private val geoCallback = object : LocationCallback() {
        override fun onLocationResult(geo: LocationResult) {
            Log.d(TAG, "onLocationResult: ${geo.locations.size}")
            for (location in geo.locations) {
                mLocation = location
                // TODO
                Log.d(
                    TAG,
                    "onLocationResult: lat: ${location.latitude}; lon: ${location.longitude}"
                )
            }
        }
    }

    // --- initial activity code
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d(TAG, "onRequestPermissionsResult: $requestCode")

        // TODO тут будет запуск MainActivity
    }

    private fun checkPermission() {
        if ((ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
            && (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            MaterialAlertDialogBuilder(this)
                .setTitle("Нам нужны геоданные")
                .setMessage("Пожалуйста, разрешите доступ к геоданным для продолжения работы приложения")
                .setPositiveButton("Ok") { _, _ ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        GEO_LOCATION_REQUEST_COD_SUCCESS
                    )
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        GEO_LOCATION_REQUEST_COD_SUCCESS
                    )
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    // ------------------- location code ------------------

}