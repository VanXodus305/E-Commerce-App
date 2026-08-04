package com.example.ecommerceapp.data.repository

import com.example.ecommerceapp.data.model.CartLineItem
import com.example.ecommerceapp.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ShopRepository {
	fun observeProducts(): Flow<List<Product>>
	fun observeProduct(productId: Int): Flow<Product?>
	fun observeCartItems(): Flow<List<CartLineItem>>
	suspend fun seedDatabaseIfEmpty()
	suspend fun refreshCatalog(source: String = "manual"): Result<Unit>
	suspend fun changeQuantity(productId: Int, delta: Int)
	suspend fun addToCart(product: Product)
	suspend fun removeFromCart(productId: Int)
	suspend fun clearCart()
}
