package com.example.agromart.model.order

data class OrderRequest (
    val data: Data=Data()
)

data class Data (
    val itemId: String="",
    val sellerId: String="",
    val quantity: Long=0,
    val price: Long=0
)
