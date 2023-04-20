package com.example.agromart.model.order

data class OrderResponse(
    val message: String = "",
    val data: DataResponse = DataResponse()
)

data class DataResponse(
    val itemId: ItemID = ItemID(),
    val buyerId: String="",
    val sellerId: String="",
    val quantity: Long=0,
    val price: Long=0,
    val status: String="",
    val qrcode: String="",
    val deliveryID: Long=0,
    val _id: String="",
    val createdAt: String="",
    val updatedAt: String="",
    val v: Long=0
)

data class ItemID(
    val _id: String = "",
    val name: String = "",
    val quantity: Long = 0,
    val mfd: String = "",
    val expiry: String = "",
    val description: String = "",
    val price: Long = 0,
    val productType: String = "",
    val sellerId: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val v: Long = 0
)

data class SellerID(
    val id: String = "",
    val phone: String = "",
    val userType: String = "",
    val productsListed: List<String> = listOf(),
    val ordersListed: List<Any?> = listOf(),
    val createdAt: String = "",
    val updatedAt: String = "",
    val v: Long = 0
)
