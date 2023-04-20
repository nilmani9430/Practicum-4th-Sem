package com.example.agromart.model.auth

data class PhoneAuthResponse(
    val hash: String = "",
    val phone: String = "",
    val otp: Int = 0
)


data class PhoneAuthVerifyResponse(
    val user: User = User(),
    val token:String="",
    val auth: Boolean = false
)

data class User(
    val ordersListed: List<Any?> = listOf(),
    val id: String = "",
    val phone: String = "",
    val userType: String = "",
    val productsListed: List<ProductsListed> = listOf(),
    val createdAt: String = "",
    val updatedAt: String = "",
    val v: Long = 0,
    val aadhar: String = "",
    val dob: String = "",
    val gender: String = "",
    val name: String = ""
)

data class ProductsListed(
    val id: String = "",
    val name: String = "",
    val quantity: Long = 0,
    val mfd: String = "",
    val expiry: String = "",
    val description: String = "",
    val price: Long = 0,
    val productType: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val v: Long = 0,
    val sellerID: String? = null
)

