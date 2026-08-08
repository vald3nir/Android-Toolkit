package com.vald3nir.toolkit.core.services.sync.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vald3nir.toolkit.core.services.analytics.notifyLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseSyncWorker(
    private val appName: String,
    private val appContext: Context,
    workerParams: WorkerParameters,
    private val ioDispatcher: CoroutineDispatcher
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun getForegroundInfo() = appContext.defaultSyncForegroundInfo(appName)

    abstract suspend fun doSafeWork(): Result

    override suspend fun doWork(): Result = withContext(ioDispatcher) {
        try {
            doSafeWork()
        } catch (e: Exception) {
            e.notifyLog()
            Result.retry()
        }
    }
}