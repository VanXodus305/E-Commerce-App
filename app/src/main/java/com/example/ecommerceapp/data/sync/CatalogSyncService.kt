package com.example.ecommerceapp.data.sync

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.ecommerceapp.data.repository.ShopRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CatalogSyncService : Service() {
	@Inject
	lateinit var repository: ShopRepository

	private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		Log.d(TAG, "Service refresh started")
		scope.launch {
			repository.refreshCatalog("service")
			stopSelf(startId)
		}
		return START_NOT_STICKY
	}

	override fun onBind(intent: Intent?): IBinder? = null

	override fun onDestroy() {
		scope.cancel()
		super.onDestroy()
	}

	companion object {
		private const val TAG = "CatalogSyncService"
	}
}
