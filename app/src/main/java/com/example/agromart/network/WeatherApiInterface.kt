package com.example.agromart.network

import com.example.agromart.model.Location
import com.example.agromart.model.Weather
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("currentWeather")
    fun fetchWeather(@Body loaction: Location):Call<Weather>
}