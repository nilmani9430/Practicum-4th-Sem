package com.example.agromart.model.product

data class ProductRequest (
    val name: String="",
    val price: Long=0,
    val quantity: Long=0,
    val mfd: String="",
    val expiry: String="",
    val description: String="",
    val productType: String=""
)
