package com.example.ecommerceapp.data.model

fun calculateCartTotal(items: List<CartLineItem>): Double =
	items.sumOf { item -> item.product.price * item.quantity }
