package com.example.agromart.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agromart.model.order.OrderRequest
import com.example.agromart.model.order.OrderResponse
import com.example.agromart.model.product.ItemListResponse
import com.example.agromart.network.CrudApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class BuyingViewModel @Inject constructor(val retrofit: Retrofit, val appContext: Application) :
    ViewModel() {
    private val _buyerItemList: MutableStateFlow<ItemListResponse> = MutableStateFlow(
        ItemListResponse()
    )
    val buyerItemList: StateFlow<ItemListResponse> get() = _buyerItemList

    private val _orderResponse: MutableStateFlow<OrderResponse> = MutableStateFlow(
        OrderResponse()
    )
    val orderResponse: StateFlow<OrderResponse> get() = _orderResponse


    private val _orderRequest: MutableStateFlow<OrderRequest> = MutableStateFlow(
        OrderRequest()
    )
    val orderRequest: StateFlow<OrderRequest> get() = _orderRequest

    private val _orderPlaced: MutableStateFlow<Boolean> = MutableStateFlow(
        false
    )
    val orderPlaced: StateFlow<Boolean> get() = _orderPlaced
    fun getItemDetail() {
        val api = retrofit.create(CrudApiInterface::class.java)
        viewModelScope.launch {
            val response = api.showAllItems()
            if (response.isSuccessful) {
                _buyerItemList.value = response.body() ?: ItemListResponse()
            } else {
                Log.d("Error", "getList: ${response.message()}")
            }
        }
    }

    fun placeOrder() {
        val api = retrofit.create(CrudApiInterface::class.java)
        viewModelScope.launch {
            val pref =
                appContext.getSharedPreferences("my_shared", Context.MODE_PRIVATE)
            val response = api.placeOrder(
                orderRequest.value,
                getHeaderMap(pref.getString("access_token", "")!!)
            )
            response.enqueue(object : Callback<OrderResponse> {
                override fun onResponse(
                    call: Call<OrderResponse>,
                    response: Response<OrderResponse>
                ) {
                    _orderResponse.value = response.body() ?: OrderResponse()
                    _orderPlaced.value=true
                }

                override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                    Log.d("Error", "fetchWeather: ${t.message}")
                }

            })
        }
    }

    fun onOrderRequestChanged(orderRequest: OrderRequest) {
        _orderRequest.value = orderRequest
    }

    fun getHeaderMap(access_token: String): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Authorization"] = "Bearer $access_token"
        return headerMap
    }

    fun onOrderChanged(a:Boolean){
        _orderPlaced.value=a
    }
}