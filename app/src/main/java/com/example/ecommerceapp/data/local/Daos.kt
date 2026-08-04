package com.example.ecommerceapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
	@Query("SELECT * FROM products ORDER BY isFeatured DESC, id ASC")
	fun observeProducts(): Flow<List<ProductEntity>>

	@Query("SELECT COUNT(*) FROM products")
	suspend fun countProducts(): Int

	@Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
	fun observeProduct(productId: Int): Flow<ProductEntity?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertAll(items: List<ProductEntity>)

	@Query("DELETE FROM products")
	suspend fun clearProducts()
}

@Dao
interface CartDao {
	@Query("SELECT * FROM cart_items ORDER BY updatedAt DESC")
	fun observeCartItems(): Flow<List<CartItemEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertItem(item: CartItemEntity)

	@Query("SELECT * FROM cart_items WHERE productId = :productId LIMIT 1")
	suspend fun getCartItem(productId: Int): CartItemEntity?

	@Query("UPDATE cart_items SET quantity = quantity + :delta, updatedAt = :updatedAt WHERE productId = :productId")
	suspend fun incrementQuantity(productId: Int, delta: Int, updatedAt: Long)

	@Query("DELETE FROM cart_items WHERE productId = :productId")
	suspend fun deleteItem(productId: Int)

	@Query("DELETE FROM cart_items")
	suspend fun clearCart()
}
