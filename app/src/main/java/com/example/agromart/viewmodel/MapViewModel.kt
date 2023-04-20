package com.example.agromart.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agromart.model.map.MapRequest
import com.example.agromart.model.map.MapResponse
import com.example.agromart.model.product.ProductResponse
import com.example.agromart.model.user.UserDetailResponse
import com.example.agromart.network.CrudApiInterface
import com.example.agromart.network.MapApiInterface
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
class MapViewModel @Inject constructor(val retrofit: Retrofit, val appContext: Application) :
    ViewModel() {
    private val _mapRequest: MutableStateFlow<MapRequest> = MutableStateFlow(
        MapRequest()
    )
    val mapRequest: StateFlow<MapRequest> get() = _mapRequest


    private val _mapResponse: MutableStateFlow<MapResponse> = MutableStateFlow(
        MapResponse()
    )
    val mapResponse: StateFlow<MapResponse> get() = _mapResponse
    fun getMap() {
        val api = retrofit.create(MapApiInterface::class.java)
        viewModelScope.launch {
            val response = api.mapNearBy(mapRequest.value)
            response.enqueue(object : Callback<MapResponse> {
                override fun onResponse(
                    call: Call<MapResponse>,
                    response: Response<MapResponse>
                ) {
                    _mapResponse.value = response.body() ?: MapResponse()
                }

                override fun onFailure(call: Call<MapResponse>, t: Throwable) {
                    Log.d("Error", "fetchWeather: ${t.message}")
                }
            })
        }
    }

    fun onMapRequestChanged(mapRequest: MapRequest) {
        _mapRequest.value = mapRequest
    }
}