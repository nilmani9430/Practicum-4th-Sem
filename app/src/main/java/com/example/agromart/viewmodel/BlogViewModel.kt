package com.example.agromart.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agromart.model.news.NewsResponse
import com.example.agromart.model.product.ItemListResponse
import com.example.agromart.model.product.ProductRequest
import com.example.agromart.network.CrudApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(val retrofit: Retrofit) : ViewModel() {
    private val _newsResponse: MutableStateFlow<NewsResponse> = MutableStateFlow(
        NewsResponse()
    )
    val newsResponse: StateFlow<NewsResponse> get() = _newsResponse

    fun getNews(){
        val api = retrofit.create(CrudApiInterface::class.java)
        viewModelScope.launch {
            val response = api.getNews()
            if(response.isSuccessful){
                _newsResponse.value=response.body()?: NewsResponse()
            } else{
                Log.d("Error", "getList: ${response.message()}")
            }
        }
    }
}

fun getHeaderMap(access_token: String): Map<String, String> {
    val headerMap = mutableMapOf<String, String>()
    headerMap["Authorization"] = "Bearer $access_token"
    return headerMap
}