package com.example.ecommerceapp.data.repository

import android.util.Log
import com.example.ecommerceapp.data.local.CartDao
import com.example.ecommerceapp.data.local.CartItemEntity
import com.example.ecommerceapp.data.local.ProductDao
import com.example.ecommerceapp.data.model.CartLineItem
import com.example.ecommerceapp.data.model.Product
import com.example.ecommerceapp.data.model.asSeedEntity
import com.example.ecommerceapp.data.model.toDomain
import com.example.ecommerceapp.data.model.toEntity
import com.example.ecommerceapp.data.remote.ProductApi
import com.example.ecommerceapp.data.prefs.UserPreferencesRepository
import com.example.ecommerceapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultShopRepository @Inject constructor(
	private val productDao: ProductDao,
	private val cartDao: CartDao,
	private val api: ProductApi,
	private val preferencesRepository: UserPreferencesRepository,
	@IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ShopRepository {

	override fun observeProducts(): Flow<List<Product>> =
		productDao.observeProducts().map { rows -> rows.map { it.toDomain() } }

	override fun observeProduct(productId: Int): Flow<Product?> =
		productDao.observeProduct(productId).map { row -> row?.toDomain() }

	override fun observeCartItems(): Flow<List<CartLineItem>> =
		combine(cartDao.observeCartItems(), observeProducts()) { cartRows, products ->
			cartRows.mapNotNull { row ->
				val product = products.firstOrNull { it.id == row.productId } ?: return@mapNotNull null
				CartLineItem(product = product, quantity = row.quantity)
			}
		}

	override suspend fun seedDatabaseIfEmpty() {
		withContext(ioDispatcher) {
			if (productDao.countProducts() > 0) return@withContext
			productDao.upsertAll(sampleCatalog().map { it.asSeedEntity() })
		}
	}

	override suspend fun refreshCatalog(source: String): Result<Unit> = withContext(ioDispatcher) {
		runCatching {
			val remoteProducts = api.fetchProducts().map { it.toDomain() }
			productDao.upsertAll(remoteProducts.map { it.toEntity() })
			preferencesRepository.setLastSync(System.currentTimeMillis())
			Log.d(TAG, "Catalog refreshed from $source with ${remoteProducts.size} items")
			Unit
		}.onFailure { error ->
			Log.w(TAG, "Refresh failed from $source, keeping cached data", error)
		}
	}

	override suspend fun changeQuantity(productId: Int, delta: Int) {
		withContext(ioDispatcher) {
			val now = System.currentTimeMillis()
			val current = cartDao.getCartItem(productId)
			if (current == null && delta > 0) {
				cartDao.upsertItem(CartItemEntity(productId = productId, quantity = delta, updatedAt = now))
			} else if (current != null) {
				val newQuantity = (current.quantity + delta).coerceAtLeast(0)
				if (newQuantity == 0) {
					cartDao.deleteItem(productId)
				} else {
					cartDao.upsertItem(current.copy(quantity = newQuantity, updatedAt = now))
				}
			}
		}
	}

	override suspend fun addToCart(product: Product) {
		changeQuantity(product.id, 1)
	}

	override suspend fun removeFromCart(productId: Int) {
		withContext(ioDispatcher) {
			cartDao.deleteItem(productId)
		}
	}

	override suspend fun clearCart() {
		withContext(ioDispatcher) {
			cartDao.clearCart()
		}
	}

	private fun sampleCatalog(): List<Product> = listOf(
		Product(
			id = 1,
			title = "Soft Cotton Tee",
			description = "A simple everyday tee for study, errands, and lazy weekends.",
			category = "Fashion",
			price = 19.99,
			imageUrl = "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab",
			rating = 4.6,
			stockLabel = "In stock",
			isFeatured = true
		),
		Product(
			id = 2,
			title = "Mini Bluetooth Speaker",
			description = "Small speaker, big sound. Easy to carry in a backpack.",
			category = "Electronics",
			price = 34.49,
			imageUrl = "https://images.unsplash.com/photo-1546435770-a3e426bf472b",
			rating = 4.3,
			stockLabel = "In stock",
			isFeatured = true
		),
		Product(
			id = 3,
			title = "Desk Mug",
			description = "A sturdy mug for study sessions and late-night coding.",
			category = "Home",
			price = 12.0,
			imageUrl = "https://images.unsplash.com/photo-1514228742587-6b1558fcca3d",
			rating = 4.1,
			stockLabel = "Low stock",
			isFeatured = false
		),
		Product(
			id = 4,
			title = "Everyday Backpack",
			description = "Simple backpack with enough room for a laptop and essentials.",
			category = "Accessories",
			price = 42.0,
			imageUrl = "https://images.unsplash.com/photo-1553062407-98eeb64c6a62",
			rating = 4.8,
			stockLabel = "In stock",
			isFeatured = true
		),
		Product(
			id = 5,
			title = "Running Shoes",
			description = "Lightweight shoes that work for walks and casual runs.",
			category = "Sports",
			price = 59.99,
			imageUrl = "https://images.unsplash.com/photo-1542291026-7eec264c27ff",
			rating = 4.5,
			stockLabel = "In stock",
			isFeatured = false
		),
		Product(
			id = 6,
			title = "Desk Lamp",
			description = "Warm light for a cozy workspace.",
			category = "Home",
			price = 29.99,
			imageUrl = "https://images.unsplash.com/photo-1517705008128-361805f42e86",
			rating = 4.0,
			stockLabel = "Low stock",
			isFeatured = false
		)
	)

	companion object {
		private const val TAG = "ShopRepository"
	}
}
