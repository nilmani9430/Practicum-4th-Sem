package com.example.agromart.model.product

import com.example.agromart.model.order.DataResponse

data class MySalesResponse(
    val message: String = "",
    val data: List<DataResponse> = listOf()
)