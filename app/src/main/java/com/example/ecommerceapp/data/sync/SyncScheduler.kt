package com.example.ecommerceapp.data.sync

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.ecommerceapp.data.sync.CatalogSyncWorker.Companion.WORK_NAME
import java.util.concurrent.TimeUnit

class SyncScheduler(private val context: Context) {
	fun schedulePeriodicSync() {
		val request = PeriodicWorkRequestBuilder<CatalogSyncWorker>(12, TimeUnit.HOURS)
			.setConstraints(
				Constraints.Builder()
					.setRequiredNetworkType(NetworkType.CONNECTED)
					.build()
			)
			.build()

		WorkManager.getInstance(context)
			.enqueueUniquePeriodicWork(WORK_NAME, androidx.work.ExistingPeriodicWorkPolicy.UPDATE, request)
	}

	fun runNow() {
		val request = OneTimeWorkRequestBuilder<CatalogSyncWorker>()
			.setConstraints(
				Constraints.Builder()
					.setRequiredNetworkType(NetworkType.CONNECTED)
					.build()
			)
			.build()

		WorkManager.getInstance(context)
			.enqueue(request)
	}
}
