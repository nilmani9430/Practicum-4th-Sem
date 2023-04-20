package com.example.agromart.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agromart.model.product.ProductRequest
import com.example.agromart.model.product.ProductResponse
import com.example.agromart.model.user.UserDetailResponse
import com.example.agromart.model.user.UserRequest
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
class ProductViewModel @Inject constructor(val retrofit: Retrofit,val appContext:Application) : ViewModel() {
    private val _productRequest: MutableStateFlow<ProductRequest> = MutableStateFlow(
        ProductRequest()
    )
    val productRequest: StateFlow<ProductRequest> get() = _productRequest

    private val _productResponse: MutableStateFlow<ProductResponse> = MutableStateFlow(
        ProductResponse()
    )
    val productResponse: StateFlow<ProductResponse> get() = _productResponse

    private val _added: MutableStateFlow<Boolean> = MutableStateFlow(
        false
    )
    val added: StateFlow<Boolean> get() = _added

    fun addProduct() {
        val api = retrofit.create(CrudApiInterface::class.java)
        viewModelScope.launch {
            val pref =
                appContext.getSharedPreferences("my_shared", Context.MODE_PRIVATE)
            val response = api.addProduct(productRequest.value,getHeaderMap(pref.getString("access_token", "")!!))
            response.enqueue(object : Callback<ProductResponse> {
                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {
                    _productResponse.value = response.body() ?: ProductResponse()
                    _added.value=true
                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    Log.d("Error", "fetchWeather: ${t.message}")
                }
            })
        }
    }

    fun mySales(){
        val api = retrofit.create(CrudApiInterface::class.java)
        viewModelScope.launch {
            val response = api.addProduct(productRequest.value,getHeaderMap(""))
            response.enqueue(object : Callback<ProductResponse> {
                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {
                    _productResponse.value = response.body() ?: ProductResponse()
                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    Log.d("Error", "fetchWeather: ${t.message}")
                }
            })
        }
    }

    fun onProductRequestChanged(productRequest: ProductRequest) {
        _productRequest.value = productRequest
    }

    fun getHeaderMap(access_token: String): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Authorization"] = "Bearer $access_token"
        return headerMap
    }

    fun onAddedChanges(added:Boolean){
        _added.value=added
    }
}