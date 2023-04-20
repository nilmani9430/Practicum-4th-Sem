package com.example.agromart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agromart.model.Location
import com.example.agromart.model.Weather
import com.example.agromart.model.product.ItemListResponse
import com.example.agromart.network.ApiInterface
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
class BuyerItemListViewModel @Inject constructor(val retrofit: Retrofit) : ViewModel() {
    private val _buyerItemList: MutableStateFlow<ItemListResponse> = MutableStateFlow(
        ItemListResponse()
    )
    val buyerItemList: StateFlow<ItemListResponse> get() = _buyerItemList
    fun getList() {
        val api = retrofit.create(CrudApiInterface::class.java)
        viewModelScope.launch {
            val response = api.showAllItems()
            if(response.isSuccessful){
                _buyerItemList.value=response.body()?:ItemListResponse()
            } else{
                Log.d("Error", "getList: ${response.message()}")
            }
        }
    }
}