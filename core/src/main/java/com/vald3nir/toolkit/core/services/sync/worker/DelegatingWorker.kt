package com.vald3nir.toolkit.core.services.sync.worker

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.vald3nir.toolkit.core.utils.extensions.defaultSyncWorkNotification
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlin.reflect.KClass

@EntryPoint
@InstallIn(SingletonComponent::class)
interface HiltWorkerFactoryEntryPoint {
    fun hiltWorkerFactory(): HiltWorkerFactory
}

private const val WORKER_CLASS_NAME = "RouterWorkerDelegateClassName"
private const val SYNC_NOTIFICATION_ID = 0

fun Context.defaultSyncForegroundInfo(appName: String) = ForegroundInfo(SYNC_NOTIFICATION_ID, defaultSyncWorkNotification(appName))

fun KClass<out CoroutineWorker>.delegatedData() = Data.Builder().putString(WORKER_CLASS_NAME, qualifiedName).build()

class DelegatingWorker(appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {

    private val workerClassName = workerParams.inputData.getString(WORKER_CLASS_NAME).orEmpty()

    private val delegateWorker = EntryPointAccessors.fromApplication<HiltWorkerFactoryEntryPoint>(appContext)
        .hiltWorkerFactory()
        .createWorker(appContext, workerClassName, workerParams) as? CoroutineWorker
        ?: throw IllegalArgumentException("Unable to find appropriate worker")

    override suspend fun getForegroundInfo(): ForegroundInfo = delegateWorker.getForegroundInfo()

    override suspend fun doWork(): Result = delegateWorker.doWork()
}
