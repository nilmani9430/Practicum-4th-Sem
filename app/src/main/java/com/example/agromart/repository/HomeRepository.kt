package com.example.agromart.repository

import com.example.agromart.model.Weather
import retrofit2.Call

interface HomeRepository {
    fun getCurrentLocation()
}