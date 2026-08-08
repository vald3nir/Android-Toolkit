package com.vald3nir.toolkit.core.services.sync.worker

import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy

inline fun <reified W : CoroutineWorker> createSyncWorkRequest() =
    OneTimeWorkRequestBuilder<DelegatingWorker>()
        .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
        .setConstraints(SyncNetworkConstraints)
        .setInputData(W::class.delegatedData())
        .build()