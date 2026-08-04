package com.example.ecommerceapp.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class PricingTest {
	@Test
	fun cartTotalAddsProductsAndQuantities() {
		val items = listOf(
			CartLineItem(
				product = Product(
					id = 1,
					title = "Test item",
					description = "Demo",
					category = "Home",
					price = 10.0,
					imageUrl = "",
					rating = 4.0,
					stockLabel = "In stock",
					isFeatured = false
				),
				quantity = 3
			)
		)

		assertEquals(30.0, calculateCartTotal(items), 0.0)
	}
}
