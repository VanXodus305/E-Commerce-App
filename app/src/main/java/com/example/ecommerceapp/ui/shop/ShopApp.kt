package com.example.ecommerceapp.ui.shop

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.ecommerceapp.data.model.CartLineItem
import com.example.ecommerceapp.data.model.Product
import com.example.ecommerceapp.data.model.SortMode
import com.example.ecommerceapp.data.sync.CatalogSyncService
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import java.text.NumberFormat
import java.util.*

private enum class ShopDestination(val route: String, val title: String, val icon: ImageVector) {
	Home("home", "Home", Icons.Default.Home),
	Cart("cart", "Cart", Icons.Default.ShoppingCart),
	Settings("settings", "Settings", Icons.Default.Settings),
	Detail("detail/{productId}", "Details", Icons.AutoMirrored.Filled.ArrowBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopApp(viewModel: ShopViewModel = hiltViewModel()) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()
	val navController = rememberNavController()
	val hazeState = remember { HazeState() }

	LaunchedEffect(Unit) {
		viewModel.clearMessage()
	}

	Scaffold(
		modifier = Modifier.fillMaxSize(),
		snackbarHost = { SnackbarHost(hostState = remember { SnackbarHostState() }) },
		bottomBar = {
			val backStackEntry by navController.currentBackStackEntryAsState()
			val currentRoute = backStackEntry?.destination?.route
			
			if (currentRoute != ShopDestination.Detail.route) {
				NavigationBar(
					modifier = Modifier
						.hazeChild(state = hazeState)
						.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
					containerColor = Color.Transparent,
					tonalElevation = 0.dp
				) {
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
													style = MaterialTheme.typography.labelSmall
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
		}
	) { paddingValues ->
		Surface(
			modifier = Modifier
				.fillMaxSize()
				.haze(hazeState)
		) {
			NavHost(
				navController = navController,
				startDestination = ShopDestination.Home.route,
				modifier = Modifier.padding(paddingValues)
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
						onSyncNow = viewModel::queueSync
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
					val quantityInCart = uiState.cartItems.find { it.product.id == productId }?.quantity ?: 0
					ProductDetailScreen(
						product = product,
						quantityInCart = quantityInCart,
						onBack = { navController.popBackStack() },
						onAddToCart = { product?.let(viewModel::addToCart) },
						onIncreaseQuantity = { viewModel.increaseQuantity(productId) },
						onDecreaseQuantity = { viewModel.decreaseQuantity(productId) }
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
	onSyncNow: () -> Unit
) {
	val cartQuantities = uiState.cartItems.associate { it.product.id to it.quantity }
	val featuredProducts = remember(uiState.products) { uiState.products.take(5) }
	val pagerState = rememberPagerState(pageCount = { featuredProducts.size })

	LazyVerticalStaggeredGrid(
		columns = StaggeredGridCells.Fixed(2),
		modifier = Modifier.fillMaxSize(),
		contentPadding = PaddingValues(16.dp),
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		verticalItemSpacing = 16.dp
	) {
		item(span = StaggeredGridItemSpan.FullLine) {
			Column(modifier = Modifier.padding(bottom = 8.dp)) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Column {
						Text(
							text = "Discover",
							style = MaterialTheme.typography.displaySmall,
							fontWeight = FontWeight.Bold,
							color = MaterialTheme.colorScheme.onBackground
						)
						Text(
							text = "Find your next favorite thing",
							style = MaterialTheme.typography.bodyMedium,
							color = MaterialTheme.colorScheme.onSurfaceVariant
						)
					}
					IconButton(
						onClick = onSyncNow,
						modifier = Modifier
							.clip(CircleShape)
							.background(MaterialTheme.colorScheme.surfaceContainerHigh)
					) {
						Icon(Icons.Default.Sync, contentDescription = "Sync", tint = MaterialTheme.colorScheme.primary)
					}
				}

				// Search Bar
				SearchBar(
					query = uiState.searchQuery,
					onQueryChange = onQueryChange,
					onSearch = {},
					active = false,
					onActiveChange = {},
					placeholder = { Text("Search products...") },
					leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
					modifier = Modifier.fillMaxWidth(),
					shape = RoundedCornerShape(16.dp),
					colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
				) {}

				Spacer(modifier = Modifier.height(24.dp))

				// Featured Pager
				if (featuredProducts.isNotEmpty()) {
					Text(
						text = "Featured Collections",
						style = MaterialTheme.typography.titleLarge,
						fontWeight = FontWeight.Bold,
						modifier = Modifier.padding(bottom = 12.dp)
					)
					HorizontalPager(
						state = pagerState,
						modifier = Modifier
							.fillMaxWidth()
							.height(220.dp)
							.clip(RoundedCornerShape(24.dp))
					) { page ->
						FeaturedCard(
							product = featuredProducts[page],
							onClick = { onProductClick(featuredProducts[page]) }
						)
					}
					
					Spacer(modifier = Modifier.height(8.dp))
					
					Row(
						Modifier
							.height(16.dp)
							.fillMaxWidth(),
						horizontalArrangement = Arrangement.Center
					) {
						repeat(featuredProducts.size) { iteration ->
							val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
							Box(
								modifier = Modifier
									.padding(2.dp)
									.clip(CircleShape)
									.background(color)
									.size(if (pagerState.currentPage == iteration) 8.dp else 6.dp)
							)
						}
					}
				}

				Spacer(modifier = Modifier.height(24.dp))

				// Categories
				Text(
					text = "Categories",
					style = MaterialTheme.typography.titleLarge,
					fontWeight = FontWeight.Bold,
					modifier = Modifier.padding(bottom = 12.dp)
				)
				LazyRow(
					horizontalArrangement = Arrangement.spacedBy(8.dp),
					modifier = Modifier.fillMaxWidth()
				) {
					items(uiState.categories) { category ->
						FilterChip(
							selected = uiState.selectedCategory == category,
							onClick = { onCategoryClick(category) },
							label = { Text(formatCategory(category)) },
							shape = RoundedCornerShape(12.dp),
							colors = FilterChipDefaults.filterChipColors(
								selectedContainerColor = MaterialTheme.colorScheme.primary,
								selectedLabelColor = MaterialTheme.colorScheme.onPrimary
							)
						)
					}
				}

				Spacer(modifier = Modifier.height(24.dp))

				// Sorting
				Text(
					text = "Sort by",
					style = MaterialTheme.typography.titleLarge,
					fontWeight = FontWeight.Bold,
					modifier = Modifier.padding(bottom = 12.dp)
				)
				LazyRow(
					horizontalArrangement = Arrangement.spacedBy(8.dp),
					modifier = Modifier.fillMaxWidth()
				) {
					items(SortMode.entries) { mode ->
						FilterChip(
							selected = uiState.sortMode == mode,
							onClick = { onSortClick(mode) },
							label = { Text(mode.label) },
							shape = RoundedCornerShape(12.dp),
							colors = FilterChipDefaults.filterChipColors(
								selectedContainerColor = MaterialTheme.colorScheme.primary,
								selectedLabelColor = MaterialTheme.colorScheme.onPrimary
							)
						)
					}
				}

				Spacer(modifier = Modifier.height(24.dp))

				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Text(
						text = "Special for you",
						style = MaterialTheme.typography.titleLarge,
						fontWeight = FontWeight.Bold
					)
					TextButton(onClick = {}) {
						Text("See all")
					}
				}
			}
		}

		items(uiState.products, key = { it.id }) { product ->
			ProductCard(
				product = product,
				onClick = { onProductClick(product) },
				quantityInCart = cartQuantities[product.id] ?: 0,
				onAddToCart = { onAddToCart(product) },
				onIncreaseQuantity = { onIncreaseQuantity(product.id) },
				onDecreaseQuantity = { onDecreaseQuantity(product.id) }
			)
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
		shape = RoundedCornerShape(20.dp),
		colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
		elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
	) {
		Column {
			Box {
				AsyncImage(
					model = product.imageUrl,
					contentDescription = product.title,
					contentScale = ContentScale.Crop,
					modifier = Modifier
						.fillMaxWidth()
						.aspectRatio(0.8f)
						.clip(RoundedCornerShape(20.dp))
				)
				
				if (quantityInCart == 0) {
					Surface(
						modifier = Modifier
							.padding(8.dp)
							.align(Alignment.TopEnd),
						shape = CircleShape,
						color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
						tonalElevation = 2.dp
					) {
						IconButton(
							onClick = onAddToCart,
							modifier = Modifier.size(36.dp)
						) {
							Icon(
								imageVector = Icons.Default.Add,
								contentDescription = "Add to cart",
								modifier = Modifier.size(20.dp),
								tint = MaterialTheme.colorScheme.primary
							)
						}
					}
				}
				
				if (quantityInCart > 0) {
					Surface(
						modifier = Modifier
							.padding(8.dp)
							.align(Alignment.TopStart),
						shape = CircleShape,
						color = MaterialTheme.colorScheme.primary,
					) {
						Text(
							text = quantityInCart.toString(),
							modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
							style = MaterialTheme.typography.labelMedium,
							color = MaterialTheme.colorScheme.onPrimary
						)
					}
				}
			}
			
			Column(modifier = Modifier.padding(8.dp)) {
				Text(
					text = product.title,
					style = MaterialTheme.typography.titleMedium,
					fontWeight = FontWeight.Bold,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)
				Text(
					text = formatCategory(product.category),
					style = MaterialTheme.typography.bodySmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Text(
						text = formatPrice(product.price),
						style = MaterialTheme.typography.titleMedium,
						fontWeight = FontWeight.ExtraBold,
						color = MaterialTheme.colorScheme.primary
					)
					
					if (quantityInCart > 0) {
						Row(
							verticalAlignment = Alignment.CenterVertically,
							horizontalArrangement = Arrangement.spacedBy(4.dp)
						) {
							FilledTonalIconButton(
								onClick = onDecreaseQuantity,
								modifier = Modifier.size(32.dp)
							) {
								Icon(Icons.Default.Remove, null, modifier = Modifier.size(16.dp))
							}
							FilledTonalIconButton(
								onClick = onIncreaseQuantity,
								modifier = Modifier.size(32.dp)
							) {
								Icon(Icons.Default.Add, null, modifier = Modifier.size(16.dp))
							}
						}
					} else {
						Row(verticalAlignment = Alignment.CenterVertically) {
							Icon(
								Icons.Default.Star,
								contentDescription = null,
								tint = Color(0xFFFFB800),
								modifier = Modifier.size(14.dp)
							)
							Text(
								text = product.rating.toString(),
								style = MaterialTheme.typography.labelSmall,
								modifier = Modifier.padding(start = 2.dp)
							)
						}
					}
				}
			}
		}
	}
}

@Composable
private fun FeaturedCard(
	product: Product,
	onClick: () -> Unit
) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.clickable(onClick = onClick)
	) {
		AsyncImage(
			model = product.imageUrl,
			contentDescription = product.title,
			contentScale = ContentScale.Crop,
			modifier = Modifier.fillMaxSize()
		)
		
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(
					Brush.verticalGradient(
						colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
						startY = 300f
					)
				)
		)
		
		Column(
			modifier = Modifier
				.align(Alignment.BottomStart)
				.padding(20.dp)
		) {
			Surface(
				shape = RoundedCornerShape(8.dp),
				color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f)
			) {
				Text(
					text = "NEW ARRIVAL",
					modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
					style = MaterialTheme.typography.labelSmall,
					fontWeight = FontWeight.Bold,
					color = MaterialTheme.colorScheme.onPrimaryContainer
				)
			}
			Spacer(modifier = Modifier.height(8.dp))
			Text(
				text = product.title,
				style = MaterialTheme.typography.headlineSmall,
				fontWeight = FontWeight.Bold,
				color = Color.White
			)
			Text(
				text = formatPrice(product.price),
				style = MaterialTheme.typography.titleLarge,
				color = Color.White.copy(alpha = 0.8f)
			)
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
	
	Column(modifier = Modifier.fillMaxSize()) {
		Text(
			text = "My Cart",
			style = MaterialTheme.typography.displaySmall,
			fontWeight = FontWeight.Bold,
			modifier = Modifier.padding(16.dp)
		)
		
		if (cartItems.isEmpty()) {
			Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
				Column(horizontalAlignment = Alignment.CenterHorizontally) {
					Icon(Icons.Default.ShoppingCart, null, modifier = Modifier.size(64.dp), tint = MaterialTheme.colorScheme.outlineVariant)
					Text("Your cart is empty", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
				}
			}
		} else {
			LazyColumn(
				modifier = Modifier.weight(1f),
				contentPadding = PaddingValues(16.dp),
				verticalArrangement = Arrangement.spacedBy(16.dp)
			) {
				val sortedItems = cartItems.sortedBy { it.product.title.lowercase(Locale.getDefault()) }
				items(sortedItems, key = { it.product.id }) { item ->
					Surface(
						shape = RoundedCornerShape(24.dp),
						color = MaterialTheme.colorScheme.surfaceContainerLow,
						onClick = {}
					) {
						Row(
							modifier = Modifier
								.padding(12.dp)
								.fillMaxWidth(),
							verticalAlignment = Alignment.CenterVertically
						) {
							AsyncImage(
								model = item.product.imageUrl,
								contentDescription = null,
								modifier = Modifier
									.size(100.dp)
									.clip(RoundedCornerShape(16.dp)),
								contentScale = ContentScale.Crop
							)
							
							Column(
								modifier = Modifier
									.weight(1f)
									.padding(horizontal = 16.dp)
							) {
								Text(item.product.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
								Text(formatCategory(item.product.category), style = MaterialTheme.typography.bodySmall)
								Spacer(Modifier.height(8.dp))
								Text(formatPrice(item.product.price), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.ExtraBold)
							}
							
							Column(horizontalAlignment = Alignment.CenterHorizontally) {
								IconButton(onClick = { onIncrease(item.product.id) }) {
									Icon(Icons.Default.Add, null)
								}
								Text(item.quantity.toString(), fontWeight = FontWeight.Bold)
								IconButton(onClick = { onDecrease(item.product.id) }) {
									Icon(Icons.Default.Remove, null)
								}
							}
						}
					}
				}
			}
			
			Surface(
				modifier = Modifier.fillMaxWidth(),
				shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
				color = MaterialTheme.colorScheme.surfaceContainerHigh,
				shadowElevation = 8.dp
			) {
				Column(modifier = Modifier.padding(24.dp)) {
					Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
						Text("Subtotal", color = MaterialTheme.colorScheme.onSurfaceVariant)
						Text(formatPrice(subtotal), fontWeight = FontWeight.Bold)
					}
					Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
						Text("Shipping", color = MaterialTheme.colorScheme.onSurfaceVariant)
						Text(formatPrice(shipping), fontWeight = FontWeight.Bold)
					}
					HorizontalDivider(Modifier.padding(vertical = 16.dp))
					Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
						Text("Total", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
						Text(formatPrice(total), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
					}
					
					Spacer(Modifier.height(24.dp))
					
					Button(
						onClick = {},
						modifier = Modifier
							.fillMaxWidth()
							.height(56.dp),
						shape = RoundedCornerShape(16.dp)
					) {
						Text("Checkout", style = MaterialTheme.typography.titleMedium)
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
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		Text("Settings", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
		
		Surface(
			shape = RoundedCornerShape(24.dp),
			color = MaterialTheme.colorScheme.surfaceContainerHigh
		) {
			Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Column {
						Text("Dark Mode", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
						Text("Switch between light and dark themes", style = MaterialTheme.typography.bodySmall)
					}
					Switch(checked = uiState.darkTheme, onCheckedChange = onThemeChange)
				}
				
				HorizontalDivider()
				
				Column {
					Text("Data Sync", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
					Text(uiState.lastSyncLabel, style = MaterialTheme.typography.bodySmall)
					Spacer(Modifier.height(12.dp))
					Button(
						onClick = onSyncNow,
						modifier = Modifier.fillMaxWidth(),
						shape = RoundedCornerShape(12.dp)
					) {
						Icon(Icons.Default.Sync, null)
						Spacer(Modifier.width(8.dp))
						Text("Sync Catalog Now")
					}
				}
			}
		}
	}
}

@Composable
private fun ProductDetailScreen(
	product: Product?,
	quantityInCart: Int,
	onBack: () -> Unit,
	onAddToCart: () -> Unit,
	onIncreaseQuantity: () -> Unit,
	onDecreaseQuantity: () -> Unit
) {
	if (product == null) return

	Box(modifier = Modifier.fillMaxSize()) {
		AsyncImage(
			model = product.imageUrl,
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.fillMaxWidth()
				.height(400.dp)
		)
		
		// Back Button
		Surface(
			modifier = Modifier
				.padding(16.dp)
				.size(48.dp)
				.align(Alignment.TopStart),
			shape = CircleShape,
			color = Color.Black.copy(alpha = 0.3f),
			onClick = onBack
		) {
			Icon(
				Icons.AutoMirrored.Filled.ArrowBack,
				contentDescription = "Back",
				tint = Color.White,
				modifier = Modifier.padding(12.dp)
			)
		}

		Surface(
			modifier = Modifier
				.fillMaxSize()
				.padding(top = 360.dp),
			shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
			color = MaterialTheme.colorScheme.background
		) {
			Column(
				modifier = Modifier
					.padding(24.dp)
					.fillMaxSize(),
				verticalArrangement = Arrangement.spacedBy(16.dp)
			) {
				Row(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Surface(
						shape = RoundedCornerShape(8.dp),
						color = MaterialTheme.colorScheme.primaryContainer
					) {
						Text(
							text = formatCategory(product.category),
							modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
							style = MaterialTheme.typography.labelMedium,
							color = MaterialTheme.colorScheme.onPrimaryContainer
						)
					}
					
					Row(verticalAlignment = Alignment.CenterVertically) {
						Icon(Icons.Default.Star, null, tint = Color(0xFFFFB800), modifier = Modifier.size(20.dp))
						Text(product.rating.toString(), fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp))
					}
				}
				
				Text(product.title, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
				
				Text(
					text = product.description,
					style = MaterialTheme.typography.bodyLarge,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
				
				Spacer(modifier = Modifier.weight(1f))
				
				Surface(
					modifier = Modifier.fillMaxWidth(),
					shape = RoundedCornerShape(24.dp),
					color = MaterialTheme.colorScheme.surfaceContainerHigh
				) {
					Row(
						modifier = Modifier.padding(16.dp),
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically
					) {
						Column {
							Text("Price", style = MaterialTheme.typography.labelMedium)
							Text(formatPrice(product.price), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
						}
						
						if (quantityInCart > 0) {
							Row(
								verticalAlignment = Alignment.CenterVertically,
								horizontalArrangement = Arrangement.spacedBy(12.dp)
							) {
								FilledTonalIconButton(
									onClick = onDecreaseQuantity,
									modifier = Modifier.size(48.dp)
								) {
									Icon(Icons.Default.Remove, null)
								}
								Text(
									text = quantityInCart.toString(),
									style = MaterialTheme.typography.titleLarge,
									fontWeight = FontWeight.Bold
								)
								FilledTonalIconButton(
									onClick = onIncreaseQuantity,
									modifier = Modifier.size(48.dp)
								) {
									Icon(Icons.Default.Add, null)
								}
							}
						} else {
							Button(
								onClick = onAddToCart,
								modifier = Modifier
									.height(56.dp)
									.width(160.dp),
								shape = RoundedCornerShape(16.dp)
							) {
								Text("Add to Cart")
							}
						}
					}
				}
			}
		}
	}
}

private fun formatPrice(value: Double): String =
	NumberFormat.getCurrencyInstance(Locale.getDefault()).format(value)

private fun formatCategory(value: String): String =
	value.trim().split(Regex("\\s+")).joinToString(" ") { segment ->
		segment.lowercase(Locale.getDefault()).replaceFirstChar { it.titlecase(Locale.getDefault()) }
	}
