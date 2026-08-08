package com.vald3nir.toolkit.servicelocation

import android.content.Context
import android.content.Intent
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class LocationWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val serviceIntent = Intent(applicationContext, LocationService::class.java)
        applicationContext.startForegroundService(serviceIntent)
        return Result.success()
    }
}

// Agendando o WorkManager para rodar periodicamente
fun scheduleWork(context: Context) {
    val request = PeriodicWorkRequestBuilder<LocationWorker>(15, TimeUnit.MINUTES).build()
    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "LocationWorker",
        ExistingPeriodicWorkPolicy.KEEP,
        request
    )
}