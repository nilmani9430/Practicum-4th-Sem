package com.example.agromart.network

import com.example.agromart.model.Location
import com.example.agromart.model.map.MapRequest
import com.example.agromart.model.map.MapResponse
import com.example.agromart.model.user.UserDetailResponse
import com.example.agromart.model.user.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MapApiInterface {
    @POST("mapNearby")
    fun mapNearBy(@Body mapRequest: MapRequest): Call<MapResponse>
}