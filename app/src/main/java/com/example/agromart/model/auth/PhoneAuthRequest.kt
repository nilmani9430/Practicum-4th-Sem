package com.example.agromart.model.auth

data class PhoneAuthRequest(
    val phone:String
)

data class PhoneAuthVerifyRequest(
    val phone:String,
    val hash:String,
    val otp:Int,
    val userType:String
)