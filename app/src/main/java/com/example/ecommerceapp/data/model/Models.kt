package com.example.ecommerceapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Product(
	val id: Int,
	val title: String,
	val description: String,
	val category: String,
	val price: Double,
	val imageUrl: String,
	val rating: Double,
	val stockLabel: String,
	val isFeatured: Boolean
)

data class CartLineItem(
	val product: Product,
	val quantity: Int
)

data class CategoryOption(
	val name: String,
	val icon: String
)

data class UserPreferences(
	val darkTheme: Boolean = false,
	val selectedCategory: String = "All",
	val selectedSortMode: String = "RatingHighToLow",
	val lastSyncAt: Long = 0L
)

enum class SortMode(val label: String) {
	RatingHighToLow("Top rated"),
	PriceLowToHigh("Price: Low"),
	PriceHighToLow("Price: High")
}

fun String.toSortMode(): SortMode =
	SortMode.entries.firstOrNull { it.name == this } ?: SortMode.RatingHighToLow

@Serializable
data class RemoteProductDto(
	val id: Int,
	val title: String,
	val price: Double,
	val description: String,
	val category: String,
	val image: String,
	val rating: RemoteRatingDto
)

@Serializable
data class RemoteRatingDto(
	val rate: Double,
	val count: Int
)

@Serializable
data class RemoteCartDto(
	val id: Int,
	val userId: Int,
	val date: String,
	val products: List<RemoteCartProductDto>
)

@Serializable
data class RemoteCartProductDto(
	@SerialName("productId") val productId: Int,
	val quantity: Int
)
