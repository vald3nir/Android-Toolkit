package com.vald3nir.toolkit.core.services.sync

interface SyncSubscriber {
    suspend fun subscribe()
}