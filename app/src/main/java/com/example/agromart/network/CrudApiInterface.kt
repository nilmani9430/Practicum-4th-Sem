package com.example.agromart.network

import com.example.agromart.model.news.NewsResponse
import com.example.agromart.model.order.OrderRequest
import com.example.agromart.model.order.OrderResponse
import com.example.agromart.model.product.ItemListResponse
import com.example.agromart.model.product.MySalesResponse
import com.example.agromart.model.product.ProductRequest
import com.example.agromart.model.product.ProductResponse
import com.example.agromart.model.user.DataRe
import com.example.agromart.model.user.UserDetailResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface CrudApiInterface {
    @POST("editUserProfile")
    fun editUser(
        @Body userRequest: DataRe,
        @HeaderMap header: Map<String, String>
    ): Call<UserDetailResponse>

    @POST("newItem")
    fun addProduct(
        @Body productRequest: ProductRequest,
        @HeaderMap header: Map<String, String>
    ): Call<ProductResponse>

    @GET("mySales")
    fun mySales(
        @Body productRequest: ProductRequest,
        @HeaderMap header: Map<String, String>
    ): Call<ProductResponse>

    @GET("showAllItems")
    suspend fun showAllItems(): Response<ItemListResponse>

    @GET("getProfile")
    fun getUserProfile(
        @HeaderMap header: Map<String, String>
    ): Call<UserDetailResponse>

    @GET("news")
    suspend fun getNews(
    ): Response<NewsResponse>

    @POST("createOrder")
    fun placeOrder(
        @Body orderRequest: OrderRequest,
        @HeaderMap headerMap: Map<String, String>
    ): Call<OrderResponse>

    @GET("mySales")
    fun mySales(@HeaderMap headerMap: Map<String, String>): Call<MySalesResponse>

}