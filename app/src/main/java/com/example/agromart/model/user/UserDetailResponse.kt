package com.example.agromart.model.user

import com.example.agromart.model.order.DataResponse
import com.example.agromart.model.order.OrderResponse

data class UserDetailResponse(
    val message: String = "",
    val data: Data = Data()
)

data class Data(
    val _id: String = "",
    val phone: String = "",
    val userType: String = "",
    val productsListed: List<Any?> = listOf(),
    val ordersListed:List<DataResponse> = listOf(),
    val createdAt: String = "",
    val updatedAt: String = "",
    val address:String="",
    val aadhar: String = "",
    val dob: String = "",
    val gender: String = "",
    val name: String = ""
)
