package com.example.agromart.model.user

data class UserRequest(
    val phone:String="",
    val name:String="",
    val gender:String="",
    val aadhar:String="",
    val dob:String="",
    val userType:String="",
    val address:String="",
)

data class DataRe(
    val data:UserRequest=UserRequest()
)
