package com.example.ecommerceapp.ui.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.model.CartLineItem
import com.example.ecommerceapp.data.model.Product
import com.example.ecommerceapp.data.model.calculateCartTotal
import com.example.ecommerceapp.data.model.SortMode
import com.example.ecommerceapp.data.model.toSortMode
import com.example.ecommerceapp.data.prefs.UserPreferencesRepository
import com.example.ecommerceapp.data.repository.ShopRepository
import com.example.ecommerceapp.data.sync.SyncScheduler
import com.example.ecommerceapp.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

data class ShopUiState(
	val products: List<Product> = emptyList(),
	val cartItems: List<CartLineItem> = emptyList(),
	val categories: List<String> = listOf("All"),
	val searchQuery: String = "",
	val selectedCategory: String = "All",
	val sortMode: SortMode = SortMode.RatingHighToLow,
	val darkTheme: Boolean = false,
	val lastSyncLabel: String = "Never synced",
	val cartCount: Int = 0,
	val cartTotal: Double = 0.0,
	val bannerText: String = "Your cart saves locally, so it still works offline.",
	val statusMessage: String? = null
)

@HiltViewModel
class ShopViewModel @Inject constructor(
	private val repository: ShopRepository,
	private val preferencesRepository: UserPreferencesRepository,
	private val syncScheduler: SyncScheduler,
	@IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

	private val searchQuery = MutableStateFlow("")
	private val statusMessage = MutableStateFlow<String?>(null)

	private val baseUiState: StateFlow<ShopUiState> = combine(
		repository.observeProducts(),
		repository.observeCartItems(),
		preferencesRepository.preferencesFlow,
		searchQuery,
	) { products, cartItems, preferences, query ->
		val category = preferences.selectedCategory
		val categories = listOf("All") + products.map { it.category }.distinct().sorted()
		val filteredProducts = products.filter { product ->
			val matchesCategory = category == "All" || product.category == category
			val matchesQuery = query.isBlank() ||
				product.title.contains(query, ignoreCase = true) ||
				product.description.contains(query, ignoreCase = true)
			matchesCategory && matchesQuery
		}
		val sortMode = preferences.selectedSortMode.toSortMode()
		val visibleProducts = when (sortMode) {
			SortMode.RatingHighToLow -> filteredProducts.sortedByDescending { it.rating }
			SortMode.PriceLowToHigh -> filteredProducts.sortedBy { it.price }
			SortMode.PriceHighToLow -> filteredProducts.sortedByDescending { it.price }
		}
		ShopUiState(
			products = visibleProducts,
			cartItems = cartItems,
			categories = categories,
			searchQuery = query,
			selectedCategory = category,
			sortMode = sortMode,
			darkTheme = preferences.darkTheme,
			lastSyncLabel = if (preferences.lastSyncAt == 0L) {
				"Never synced"
			} else {
				"Last sync: ${Date(preferences.lastSyncAt)}"
			},
			cartCount = cartItems.sumOf { it.quantity },
			cartTotal = calculateCartTotal(cartItems)
		)
	}.stateIn(
		viewModelScope,
		SharingStarted.WhileSubscribed(5_000),
		ShopUiState()
	)

	val uiState: StateFlow<ShopUiState> = baseUiState
		.combine(statusMessage) { state, message ->
			state.copy(statusMessage = message)
		}
		.stateIn(
			viewModelScope,
			SharingStarted.WhileSubscribed(5_000),
			ShopUiState()
		)

	init {
		viewModelScope.launch(ioDispatcher) {
			repository.seedDatabaseIfEmpty()
			repository.refreshCatalog("startup")
			syncScheduler.schedulePeriodicSync()
		}
	}

	fun onSearchChange(value: String) {
		searchQuery.value = value
	}

	fun onCategorySelected(category: String) {
		viewModelScope.launch(ioDispatcher) {
			preferencesRepository.setSelectedCategory(category)
		}
	}

	fun onSortSelected(mode: SortMode) {
		viewModelScope.launch(ioDispatcher) {
			preferencesRepository.setSelectedSortMode(mode)
		}
	}

	fun toggleTheme(enabled: Boolean) {
		viewModelScope.launch(ioDispatcher) {
			preferencesRepository.setDarkTheme(enabled)
			statusMessage.value = if (enabled) "Dark theme saved" else "Light theme saved"
		}
	}

	fun addToCart(product: Product) {
		viewModelScope.launch(ioDispatcher) {
			repository.addToCart(product)
			statusMessage.value = "${product.title} added to cart"
		}
	}

	fun increaseQuantity(productId: Int) {
		viewModelScope.launch(ioDispatcher) {
			repository.changeQuantity(productId, 1)
		}
	}

	fun decreaseQuantity(productId: Int) {
		viewModelScope.launch(ioDispatcher) {
			repository.changeQuantity(productId, -1)
		}
	}

	fun removeFromCart(productId: Int) {
		viewModelScope.launch(ioDispatcher) {
			repository.removeFromCart(productId)
		}
	}

	fun clearCart() {
		viewModelScope.launch(ioDispatcher) {
			repository.clearCart()
			statusMessage.value = "Cart cleared"
		}
	}

	fun queueSync() {
		syncScheduler.runNow()
		statusMessage.value = "Sync queued with WorkManager"
	}

	fun clearMessage() {
		statusMessage.value = null
	}
}
