package com.example.ecommerceapp.data.remote

import com.example.ecommerceapp.data.model.RemoteProductDto
import retrofit2.http.GET

interface ProductApi {
	@GET("products")
	suspend fun fetchProducts(): List<RemoteProductDto>
}
