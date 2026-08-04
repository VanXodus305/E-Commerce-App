package com.example.ecommerceapp.ui.shop

import android.content.Intent
import android.net.Uri
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ecommerceapp.data.model.CartLineItem
import com.example.ecommerceapp.data.model.Product
import com.example.ecommerceapp.data.model.SortMode
import com.example.ecommerceapp.data.sync.CatalogSyncService
import java.text.NumberFormat
import java.util.Locale

private enum class ShopDestination(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
	Home("home", "Home", Icons.Default.Home),
	Cart("cart", "Cart", Icons.Default.ShoppingCart),
	Settings("settings", "Settings", Icons.Default.Settings),
	Detail("detail/{productId}", "Details", Icons.AutoMirrored.Filled.ArrowBack)
}

@Composable
fun ShopApp(viewModel: ShopViewModel = hiltViewModel()) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()
	val navController = rememberNavController()

	LaunchedEffect(Unit) {
		viewModel.clearMessage()
	}

	Scaffold(
		modifier = Modifier.fillMaxSize(),
		snackbarHost = { SnackbarHost(hostState = remember { SnackbarHostState() }) },
		bottomBar = {
			val backStackEntry by navController.currentBackStackEntryAsState()
			val currentRoute = backStackEntry?.destination?.route
			NavigationBar {
				listOf(ShopDestination.Home, ShopDestination.Cart, ShopDestination.Settings).forEach { destination ->
					NavigationBarItem(
						selected = currentRoute == destination.route,
						onClick = {
							navController.navigate(destination.route) {
								popUpTo(navController.graph.startDestinationId) { saveState = true }
								launchSingleTop = true
								restoreState = true
							}
						},
						icon = {
							if (destination == ShopDestination.Cart && uiState.cartCount > 0) {
								BadgedBox(
									badge = {
										Badge(
											containerColor = MaterialTheme.colorScheme.primary,
											contentColor = MaterialTheme.colorScheme.onPrimary
										) {
											Text(
												text = if (uiState.cartCount > 99) "99+" else uiState.cartCount.toString(),
												style = MaterialTheme.typography.labelMedium,
												modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
											)
										}
									}
								) {
									Icon(destination.icon, contentDescription = null)
								}
							} else {
								Icon(destination.icon, contentDescription = null)
							}
						},
						label = { Text(destination.title) }
					)
				}
			}
		}
	) { paddingValues ->
		Surface(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
			NavHost(
				navController = navController,
				startDestination = ShopDestination.Home.route
			) {
				composable(ShopDestination.Home.route) {
					HomeScreen(
						uiState = uiState,
						onProductClick = { product -> navController.navigate("detail/${product.id}") },
						onAddToCart = viewModel::addToCart,
						onIncreaseQuantity = viewModel::increaseQuantity,
						onDecreaseQuantity = viewModel::decreaseQuantity,
						onSortClick = viewModel::onSortSelected,
						onQueryChange = viewModel::onSearchChange,
						onCategoryClick = viewModel::onCategorySelected,
						onSyncNow = viewModel::queueSync,
						onOpenServiceSync = { context ->
							context.startService(Intent(context, CatalogSyncService::class.java))
						}
					)
				}
				composable(ShopDestination.Cart.route) {
					CartScreen(
						cartItems = uiState.cartItems,
						onIncrease = viewModel::increaseQuantity,
						onDecrease = viewModel::decreaseQuantity,
						onRemove = viewModel::removeFromCart,
						onClear = viewModel::clearCart
					)
				}
				composable(ShopDestination.Settings.route) {
					SettingsScreen(
						uiState = uiState,
						onThemeChange = viewModel::toggleTheme,
						onSyncNow = viewModel::queueSync
					)
				}
				composable(
					route = ShopDestination.Detail.route,
					arguments = listOf(navArgument("productId") { type = NavType.IntType })
				) { entry ->
					val productId = entry.arguments?.getInt("productId") ?: 0
					val product = uiState.products.firstOrNull { it.id == productId }
					ProductDetailScreen(
						product = product,
						onBack = { navController.popBackStack() },
						onAddToCart = { product?.let(viewModel::addToCart) }
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun HomeScreen(
	uiState: ShopUiState,
	onProductClick: (Product) -> Unit,
	onAddToCart: (Product) -> Unit,
	onIncreaseQuantity: (Int) -> Unit,
	onDecreaseQuantity: (Int) -> Unit,
	onSortClick: (SortMode) -> Unit,
	onQueryChange: (String) -> Unit,
	onCategoryClick: (String) -> Unit,
	onSyncNow: () -> Unit,
	onOpenServiceSync: (Context) -> Unit
) {
	val context = LocalContext.current
	val cartQuantities = uiState.cartItems.associate { it.product.id to it.quantity }
	val featuredProducts = remember(uiState.products) {
		uiState.products.take(5)
	}

	LazyColumn(
		modifier = Modifier.fillMaxSize(),
		contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 12.dp),
		verticalArrangement = Arrangement.spacedBy(10.dp)
	) {
		item {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Column {
					Text("E Commerce App", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
					Text("Browse, filter, and build your cart", style = MaterialTheme.typography.bodyMedium)
				}
				AssistChip(
					onClick = onSyncNow,
					label = { Text("Refresh") },
					leadingIcon = { Icon(Icons.Default.Refresh, null) }
				)
			}
		}
		item {
			Card(
				shape = RoundedCornerShape(28.dp),
				colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
				elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
			) {
				Column(
					modifier = Modifier.padding(16.dp),
					verticalArrangement = Arrangement.spacedBy(12.dp)
				) {
					Text(
						text = "Featured picks",
						style = MaterialTheme.typography.titleLarge,
						fontWeight = FontWeight.Bold
					)
					Text(
						text = "Curated items with fast access to details, cart controls, and live sync.",
						style = MaterialTheme.typography.bodyMedium
					)
					LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
						items(featuredProducts, key = { it.id }) { product ->
							FeaturedCard(
								product = product,
								onClick = { onProductClick(product) },
								onAddToCart = { onAddToCart(product) }
							)
						}
					}
					uiState.statusMessage?.let {
						Text(it, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Medium)
					}
				}
			}
		}
		item {
			OutlinedTextField(
				value = uiState.searchQuery,
				onValueChange = onQueryChange,
				modifier = Modifier.fillMaxWidth(),
				label = { Text("Search products") },
				singleLine = true
			)
		}
		item {
			Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
				AssistChip(
					onClick = {},
					label = { Text("${uiState.cartCount} items") },
					leadingIcon = { Icon(Icons.Default.ShoppingCart, null) }
				)
				AssistChip(
					onClick = {},
					label = { Text("${formatPrice(uiState.cartTotal)} total") },
					leadingIcon = { Icon(Icons.Default.Category, null) }
				)
			}
		}
		item {
			Text(
				text = "Categories",
				style = MaterialTheme.typography.titleSmall,
				fontWeight = FontWeight.SemiBold
			)
		}
		item {
			FlowRow(
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp)
			) {
				uiState.categories.forEach { category ->
					FilterChip(
						selected = uiState.selectedCategory == category,
						onClick = { onCategoryClick(category) },
						label = { Text(formatCategory(category)) }
					)
				}
			}
		}
		item {
			Text(
				text = "Sort by",
				style = MaterialTheme.typography.titleSmall,
				fontWeight = FontWeight.SemiBold
			)
		}
		item {
			Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
				SortMode.entries.forEach { mode ->
					FilterChip(
						selected = uiState.sortMode == mode,
						onClick = { onSortClick(mode) },
						label = { Text(mode.label) }
					)
				}
			}
		}
		items(uiState.products) { product ->
			ProductCard(
				product = product,
				onClick = { onProductClick(product) },
				quantityInCart = cartQuantities[product.id] ?: 0,
				onAddToCart = { onAddToCart(product) },
				onIncreaseQuantity = { onIncreaseQuantity(product.id) },
				onDecreaseQuantity = { onDecreaseQuantity(product.id) }
			)
		}
		if (uiState.products.isEmpty()) {
			item {
				Card(
					shape = RoundedCornerShape(20.dp),
					colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest)
				) {
					Text(
						text = "No products match your search yet.",
						style = MaterialTheme.typography.bodyLarge,
						modifier = Modifier.padding(16.dp)
					)
				}
			}
		}
	}
}

@Composable
private fun ProductCard(
	product: Product,
	onClick: () -> Unit,
	quantityInCart: Int,
	onAddToCart: () -> Unit,
	onIncreaseQuantity: () -> Unit,
	onDecreaseQuantity: () -> Unit
) {
	Card(
		onClick = onClick,
		modifier = Modifier.fillMaxWidth(),
		shape = RoundedCornerShape(24.dp),
		colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
		elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
	) {
		Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
			AsyncImage(
				model = product.imageUrl,
				contentDescription = product.title,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.fillMaxWidth()
					.height(180.dp)
					.clip(RoundedCornerShape(20.dp))
			)
			CategoryBadge(formatCategory(product.category))
			Text(product.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
			Text(product.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Column {
					Text(formatPrice(product.price), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
					Text("${product.rating} rating · ${product.stockLabel}")
				}
				if (quantityInCart > 0) {
					Row(verticalAlignment = Alignment.CenterVertically) {
						OutlinedButton(onClick = onDecreaseQuantity) {
							Icon(Icons.Default.Remove, contentDescription = "Decrease quantity")
						}
						Text(
							text = quantityInCart.toString(),
							style = MaterialTheme.typography.titleMedium,
							fontWeight = FontWeight.Bold,
							modifier = Modifier.padding(horizontal = 12.dp)
						)
						OutlinedButton(onClick = onIncreaseQuantity) {
							Icon(Icons.Default.Add, contentDescription = "Increase quantity")
						}
					}
				} else {
					Button(onClick = onAddToCart) {
						Icon(Icons.Default.Add, contentDescription = null)
						Spacer(Modifier.size(4.dp))
						Text("Add")
					}
				}
			}
		}
	}
}

@Composable
private fun FeaturedCard(
	product: Product,
	onClick: () -> Unit,
	onAddToCart: () -> Unit
) {
	Card(
		onClick = onClick,
		modifier = Modifier
			.width(210.dp)
			.height(220.dp),
		shape = RoundedCornerShape(24.dp),
		colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
		elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
	) {
		Column(
			modifier = Modifier.padding(12.dp),
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			AsyncImage(
				model = product.imageUrl,
				contentDescription = product.title,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.fillMaxWidth()
					.height(110.dp)
					.clip(RoundedCornerShape(18.dp))
			)
			CategoryBadge(formatCategory(product.category))
			Text(product.title, maxLines = 2, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.SemiBold)
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(formatPrice(product.price), fontWeight = FontWeight.Bold)
				OutlinedButton(onClick = onAddToCart) {
					Icon(Icons.Default.Add, contentDescription = null)
				}
			}
		}
	}
}

@Composable
private fun CartScreen(
	cartItems: List<CartLineItem>,
	onIncrease: (Int) -> Unit,
	onDecrease: (Int) -> Unit,
	onRemove: (Int) -> Unit,
	onClear: () -> Unit
) {
	val subtotal = cartItems.sumOf { it.product.price * it.quantity }
	val shipping = if (cartItems.isEmpty()) 0.0 else 25.0
	val total = subtotal + shipping
	val orderedItems = remember(cartItems) {
		cartItems.sortedBy { it.product.title.lowercase(Locale.getDefault()) }
	}
	LazyColumn(
		modifier = Modifier.fillMaxSize(),
		contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 12.dp),
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		item {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Column {
					Text("Your cart", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
					Text("${cartItems.sumOf { it.quantity }} items ready to check out")
				}
				AssistChip(onClick = {}, label = { Text(formatPrice(subtotal)) }, leadingIcon = { Icon(Icons.Default.ShoppingCart, null) })
			}
		}
		items(orderedItems, key = { it.product.id }) { item ->
			Card(
				modifier = Modifier.fillMaxWidth(),
				shape = RoundedCornerShape(24.dp),
				colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
				elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
			) {
				Row(
					modifier = Modifier.padding(14.dp),
					horizontalArrangement = Arrangement.spacedBy(12.dp),
					verticalAlignment = Alignment.Top
				) {
					AsyncImage(
						model = item.product.imageUrl,
						contentDescription = item.product.title,
						contentScale = ContentScale.Crop,
						modifier = Modifier
							.size(86.dp)
							.clip(RoundedCornerShape(18.dp))
					)
					Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
						CategoryBadge(formatCategory(item.product.category))
						Text(item.product.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
						Text(formatPrice(item.product.price), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
						Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
							FilledTonalIconButton(onClick = { onDecrease(item.product.id) }) {
								Icon(Icons.Default.Remove, contentDescription = "Decrease quantity")
							}
							Text(
								text = item.quantity.toString(),
								style = MaterialTheme.typography.titleMedium,
								fontWeight = FontWeight.Bold
							)
							FilledTonalIconButton(onClick = { onIncrease(item.product.id) }) {
								Icon(Icons.Default.Add, contentDescription = "Increase quantity")
							}
						}
					}
					TextButton(onClick = { onRemove(item.product.id) }) {
						Icon(Icons.Default.Delete, null)
						Spacer(Modifier.size(4.dp))
						Text("Remove")
					}
				}
			}
		}
		item {
			Card(
				shape = RoundedCornerShape(28.dp),
				colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
				elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
			) {
				Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
					Text("Order summary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
					Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
						Text("Subtotal")
						Text(formatPrice(subtotal))
					}
					Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
						Text("Shipping")
						Text(if (shipping == 0.0) "Free" else formatPrice(shipping))
					}
					HorizontalDivider()
					Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
						Text("Total", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
						Text(formatPrice(total), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
					}
					Button(
						onClick = onClear,
						modifier = Modifier.fillMaxWidth()
					) {
						Text("Clear cart")
					}
				}
			}
		}
	}
}

@Composable
private fun SettingsScreen(
	uiState: ShopUiState,
	onThemeChange: (Boolean) -> Unit,
	onSyncNow: () -> Unit
) {
	Column(
		modifier = Modifier.fillMaxSize().padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		Text("Settings", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
		Card {
			Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
				Row(verticalAlignment = Alignment.CenterVertically) {
					Checkbox(
						checked = uiState.darkTheme,
						onCheckedChange = onThemeChange
					)
					Text("Dark theme")
				}
				Text("Saved in DataStore so it survives app restarts.")
				Text(uiState.lastSyncLabel)
				Button(onClick = onSyncNow) {
					Icon(Icons.Default.Refresh, null)
					Spacer(Modifier.size(4.dp))
					Text("Queue background sync")
				}
			}
		}
	}
}

@Composable
private fun ProductDetailScreen(
	product: Product?,
	onBack: () -> Unit,
	onAddToCart: () -> Unit
) {
	if (product == null) {
		Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
			Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
				Text("Product not found")
				OutlinedButton(onClick = onBack) { Text("Back") }
			}
		}
		return
	}

	Column(
		modifier = Modifier.fillMaxSize().padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		Row(verticalAlignment = Alignment.CenterVertically) {
			IconButton(onClick = onBack) {
				Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
			}
			Text("Product details", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
		}
		Card(
			shape = RoundedCornerShape(28.dp),
			colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
			elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
		) {
			Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
				AsyncImage(
					model = product.imageUrl,
					contentDescription = product.title,
					contentScale = ContentScale.Crop,
					modifier = Modifier
						.fillMaxWidth()
						.height(270.dp)
						.clip(RoundedCornerShape(22.dp))
				)
				CategoryBadge(formatCategory(product.category))
				Text(product.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
				Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
					AssistChip(onClick = {}, label = { Text("${product.rating} rating") }, leadingIcon = { Icon(Icons.Default.Refresh, null) })
					AssistChip(onClick = {}, label = { Text(product.stockLabel) }, leadingIcon = { Icon(Icons.Default.Category, null) })
				}
				Text(product.description, style = MaterialTheme.typography.bodyLarge)
				Text(formatPrice(product.price), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
				Button(onClick = onAddToCart, modifier = Modifier.fillMaxWidth()) {
					Icon(Icons.Default.Add, contentDescription = null)
					Spacer(Modifier.size(4.dp))
					Text("Add to cart")
				}
			}
		}
	}
}

private fun formatPrice(value: Double): String =
	NumberFormat.getCurrencyInstance(Locale.getDefault()).format(value)

private fun formatCategory(value: String): String =
	value.trim()
		.split(Regex("\\s+"))
	.joinToString(" ") { segment ->
		segment.lowercase(Locale.getDefault()).replaceFirstChar { ch ->
			ch.titlecase(Locale.getDefault())
		}
	}

@Composable
private fun CategoryBadge(text: String) {
	AssistChip(
		onClick = {},
		label = { Text(text) },
		modifier = Modifier.shadow(0.dp, RoundedCornerShape(999.dp))
	)
}
