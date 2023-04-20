package com.example.agromart.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agromart.model.Location
import com.example.agromart.model.Weather
import com.example.agromart.model.product.MySalesResponse
import com.example.agromart.model.user.UserDetailResponse
import com.example.agromart.model.user.UserRequest
import com.example.agromart.network.ApiInterface
import com.example.agromart.network.CrudApiInterface
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
class SalesViewModel @Inject constructor(val retrofit: Retrofit, val appContext: Application) :
    ViewModel() {

    private val _mySalesResponse: MutableStateFlow<MySalesResponse> = MutableStateFlow(
        MySalesResponse()
    )
    val mySalesResponse: StateFlow<MySalesResponse> get() = _mySalesResponse

    fun getSales() {
        val api = retrofit.create(CrudApiInterface::class.java)
        viewModelScope.launch {
            val pref =
                appContext.getSharedPreferences("my_shared", Context.MODE_PRIVATE)
            val response = api.mySales(
                getHeaderMap(pref.getString("access_token", "")!!)
            )
            response.enqueue(object : Callback<MySalesResponse> {
                override fun onResponse(call: Call<MySalesResponse>, response: Response<MySalesResponse>) {
                    _mySalesResponse.value = response.body() ?: MySalesResponse()
                }

                override fun onFailure(call: Call<MySalesResponse>, t: Throwable) {
                    Log.d("Error", "fetchWeather: ${t.message}")
                }

            })
        }
    }

    fun getHeaderMap(access_token: String): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Authorization"] = "Bearer $access_token"
        return headerMap
    }
}