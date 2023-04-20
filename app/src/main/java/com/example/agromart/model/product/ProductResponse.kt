package com.example.agromart.model.product

data class ProductResponse(
    val message: String = "",
    val data: Data = Data()
)

data class Data(
    val name: String = "",
    val quantity: Long = 0,
    val mfd: String = "",
    val expiry: String = "",
    val description: String = "",
    val price: Long = 0,
    val productType: String = "",
    val sellerID: String = "",
    val id: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val v: Long = 0
)
