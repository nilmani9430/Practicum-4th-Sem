package com.example.agromart.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agromart.model.auth.PhoneAuthVerifyResponse
import com.example.agromart.model.product.ItemListResponse
import com.example.agromart.model.user.UserDetailResponse
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
class OrderViewModel @Inject constructor(val retrofit: Retrofit, val appContext: Application) :
    ViewModel() {
    private val _userDetailResponse: MutableStateFlow<UserDetailResponse> = MutableStateFlow(
        UserDetailResponse()
    )
    val userDetailResponse: StateFlow<UserDetailResponse> get() = _userDetailResponse

    fun getUserProfile() {
        val api = retrofit.create(CrudApiInterface::class.java)
        viewModelScope.launch {
            val pref =
                appContext.getSharedPreferences("my_shared", Context.MODE_PRIVATE)
            val response = api.getUserProfile(getHeaderMap(pref.getString("access_token", "")!!))
            response.enqueue(object : Callback<UserDetailResponse> {
                override fun onResponse(
                    call: Call<UserDetailResponse>,
                    response: Response<UserDetailResponse>
                ) {
                    _userDetailResponse.value=(response.body() ?: UserDetailResponse())
                }

                override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                    Log.d("Error", "fetchWeather: ${t.message}")
                }
            })
        }
    }
}
