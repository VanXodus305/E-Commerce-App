package com.example.ecommerceapp.data.model

import com.example.ecommerceapp.data.local.CartItemEntity
import com.example.ecommerceapp.data.local.ProductEntity

fun ProductEntity.toDomain(): Product = Product(
	id = id,
	title = title,
	description = description,
	category = category,
	price = price,
	imageUrl = imageUrl,
	rating = rating,
	stockLabel = stockLabel,
	isFeatured = isFeatured
)

fun Product.toEntity(): ProductEntity = ProductEntity(
	id = id,
	title = title,
	description = description,
	category = category,
	price = price,
	imageUrl = imageUrl,
	rating = rating,
	stockLabel = stockLabel,
	isFeatured = isFeatured
)

fun RemoteProductDto.toDomain(): Product = Product(
	id = id,
	title = title,
	description = description,
	category = category,
	price = price,
	imageUrl = image,
	rating = rating.rate,
	stockLabel = if (rating.count > 80) "In stock" else "Low stock",
	isFeatured = rating.rate >= 4.0
)

fun CartItemEntity.toDomain(product: Product): CartLineItem =
	CartLineItem(product = product, quantity = quantity)

fun Product.seedCopy(featured: Boolean = isFeatured): Product = copy(
	isFeatured = featured
)

fun Product.asSeedEntity(): ProductEntity = toEntity()
