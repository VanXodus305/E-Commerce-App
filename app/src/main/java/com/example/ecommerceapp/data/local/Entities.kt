package com.example.ecommerceapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
	@PrimaryKey val id: Int,
	val title: String,
	val description: String,
	val category: String,
	val price: Double,
	val imageUrl: String,
	val rating: Double,
	val stockLabel: String,
	val isFeatured: Boolean
)

@Entity(tableName = "cart_items")
data class CartItemEntity(
	@PrimaryKey val productId: Int,
	val quantity: Int,
	val updatedAt: Long
)
