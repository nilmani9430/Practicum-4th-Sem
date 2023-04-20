package com.example.agromart.model.product

data class ItemListResponse(
    val message: String="",
    val data: List<Datum> = listOf()
)

data class Datum(
    val _id: String = "",
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
    val sellerId: String = ""
)
