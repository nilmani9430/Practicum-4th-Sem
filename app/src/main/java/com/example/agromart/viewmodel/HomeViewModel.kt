package com.example.agromart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agromart.model.Location
import com.example.agromart.model.Weather
import com.example.agromart.network.ApiInterface
import com.example.agromart.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val homeRepository: HomeRepository,
    val retrofit: Retrofit
) : ViewModel() {
    private val _weather: MutableStateFlow<Weather> = MutableStateFlow(Weather())
    val weather: StateFlow<Weather> get() = _weather

    fun fetchWeather(lat: Double, long: Double) {
        val api = retrofit.create(ApiInterface::class.java)
        Log.d("Error2", "fetchWeather: ${lat} $long")
        viewModelScope.launch {
            val response = api.fetchWeather(Location(lat, long))
            response.enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    _weather.value = response.body()?:Weather()
                }
                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    Log.d("Error", "fetchWeather: ${t.message}")
                }

            })
        }
    }
}