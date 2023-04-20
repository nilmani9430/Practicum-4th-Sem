package com.example.agromart.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agromart.model.Weather
import com.example.agromart.model.auth.PhoneAuthRequest
import com.example.agromart.model.auth.PhoneAuthResponse
import com.example.agromart.model.auth.PhoneAuthVerifyRequest
import com.example.agromart.model.auth.PhoneAuthVerifyResponse
import com.example.agromart.model.user.UserDetailResponse
import com.example.agromart.model.user.UserRequest
import com.example.agromart.network.AuthApiInterface
import com.example.agromart.network.CrudApiInterface
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
class LoginViewModel @Inject constructor(
    val retrofit: Retrofit,
    val appContext: Application
) : ViewModel() {
    private val _phoneNumber: MutableStateFlow<String> = MutableStateFlow("")
    val phoneNumber: StateFlow<String> get() = _phoneNumber

    private val _otp: MutableStateFlow<String> = MutableStateFlow("")
    val otp: StateFlow<String> get() = _otp

    private val _otpField: MutableStateFlow<String> = MutableStateFlow("")
    val otpField: StateFlow<String> get() = _otpField

    private val _isLogged: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLogged: StateFlow<Boolean> get() = _isLogged

    private val _hash: MutableStateFlow<String> = MutableStateFlow("")
    val hash: StateFlow<String> get() = _hash


    fun sendOTP() {
        val api = retrofit.create(AuthApiInterface::class.java)
        viewModelScope.launch {
            val response = api.sendOTP(PhoneAuthRequest(phoneNumber.value))
            response.enqueue(object : Callback<PhoneAuthResponse> {
                override fun onResponse(
                    call: Call<PhoneAuthResponse>,
                    response: Response<PhoneAuthResponse>
                ) {
                    (response.body() ?: PhoneAuthResponse()).let {
                        _otp.value = it.otp.toString()
                        _hash.value = it.hash
                    }

                }

                override fun onFailure(call: Call<PhoneAuthResponse>, t: Throwable) {
                    Log.d("Error", "fetchWeather: ${t.message}")
                }
            })
        }
    }

    fun onPhoneNumberChanged(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    fun onOtpChanged(otp: String) {
        _otpField.value = otp

        if (otpField.value == this.otp.value) {
            val api = retrofit.create(AuthApiInterface::class.java)
            viewModelScope.launch {
                val response = api.verifyOTP(
                    PhoneAuthVerifyRequest(
                        phoneNumber.value,
                        hash = hash.value,
                        otp.toInt(),
                        "Buyer"
                    )
                )
                response.enqueue(object : Callback<PhoneAuthVerifyResponse> {
                    override fun onResponse(
                        call: Call<PhoneAuthVerifyResponse>,
                        response: Response<PhoneAuthVerifyResponse>
                    ) {
                        val pref =
                            appContext.getSharedPreferences("my_shared", Context.MODE_PRIVATE)

                        (response.body() ?: PhoneAuthVerifyResponse()).let {
                            pref.edit().apply{
                                putString("access_token",it.token)
                                putBoolean("isLogged",true)
                            }.apply()
                        }
                        _isLogged.value=true
                    }

                    override fun onFailure(call: Call<PhoneAuthVerifyResponse>, t: Throwable) {
                        Log.d("Error", "fetchWeather: ${t.message}")
                    }
                })
            }
        }
    }

    fun onLoggedChanges(isLogged:Boolean){
        _isLogged.value=isLogged
    }
}