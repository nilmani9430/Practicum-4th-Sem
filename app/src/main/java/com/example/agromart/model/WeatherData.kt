package com.example.agromart.model

data class Weather(
    val data: WeatherData= WeatherData()
)

data class Location(
    val lat: Double =0.0,
    val long: Double =0.0
)

data class WeatherData(
    val temperature: Float=0.0f,
    val windspeed: Float=0.0f,
    val winddirection: Float=0.0f,
    val is_day: Int=0,
)