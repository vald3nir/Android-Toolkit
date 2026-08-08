package com.vald3nir.toolkit.core.services.sync.worker

import androidx.work.Constraints
import androidx.work.NetworkType

val SyncNetworkConstraints
    get() = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()