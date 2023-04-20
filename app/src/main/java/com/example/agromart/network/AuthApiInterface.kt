package com.example.agromart.network

import com.example.agromart.model.auth.PhoneAuthRequest
import com.example.agromart.model.auth.PhoneAuthResponse
import com.example.agromart.model.auth.PhoneAuthVerifyRequest
import com.example.agromart.model.auth.PhoneAuthVerifyResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiInterface {
    @POST("sendOTP")
    fun sendOTP(@Body phoneAuthRequest: PhoneAuthRequest): Call<PhoneAuthResponse>

    @POST("verifyOTP")
    fun verifyOTP(@Body phoneAuthVerifyRequest: PhoneAuthVerifyRequest): Call<PhoneAuthVerifyResponse>
}