package com.example.ecommerceapp.di

import android.content.Context
import androidx.room.Room
import com.example.ecommerceapp.data.local.AppDatabase
import com.example.ecommerceapp.data.remote.ProductApi
import com.example.ecommerceapp.data.repository.DefaultShopRepository
import com.example.ecommerceapp.data.repository.ShopRepository
import com.example.ecommerceapp.data.sync.SyncScheduler
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

	@Binds
	abstract fun bindShopRepository(
		impl: DefaultShopRepository
	): ShopRepository

	companion object {
		@Provides
		@Singleton
		fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
			Room.databaseBuilder(context, AppDatabase::class.java, "ecommerce.db")
				.fallbackToDestructiveMigration(dropAllTables = true)
				.build()

		@Provides
		fun provideProductDao(database: AppDatabase) = database.productDao()

		@Provides
		fun provideCartDao(database: AppDatabase) = database.cartDao()

		@Provides
		@Singleton
		fun provideOkHttpClient(): OkHttpClient =
			OkHttpClient.Builder()
				.connectTimeout(15, TimeUnit.SECONDS)
				.readTimeout(15, TimeUnit.SECONDS)
				.writeTimeout(15, TimeUnit.SECONDS)
				.build()

		@Provides
		@Singleton
		fun provideJson(): Json = Json {
			ignoreUnknownKeys = true
			prettyPrint = false
		}

		@Provides
		@Singleton
		fun provideRetrofit(
			okHttpClient: OkHttpClient,
			json: Json
		): Retrofit {
			val contentType = "application/json".toMediaType()
			return Retrofit.Builder()
				.baseUrl("https://fakestoreapi.com/")
				.client(okHttpClient)
				.addConverterFactory(json.asConverterFactory(contentType))
				.build()
		}

		@Provides
		@Singleton
		fun provideProductApi(retrofit: Retrofit): ProductApi =
			retrofit.create(ProductApi::class.java)

		@Provides
		@Singleton
		fun provideSyncScheduler(@ApplicationContext context: Context) = SyncScheduler(context)

		@Provides
		@IoDispatcher
		fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
	}
}
