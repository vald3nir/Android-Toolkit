package com.vald3nir.toolkit.core.services.sync.monitors

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest.Builder
import androidx.core.content.getSystemService
import com.vald3nir.toolkit.core.services.threads.Dispatcher
import com.vald3nir.toolkit.core.services.threads.JobScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}

internal class NetworkMonitorImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @Dispatcher(JobScope.IO) private val ioDispatcher: CoroutineDispatcher,
) : NetworkMonitor {

    override val isOnline: Flow<Boolean> = callbackFlow {
        val connectivityManager = context.getSystemService<ConnectivityManager>() ?: run {
            trySend(false)
            close()
            return@callbackFlow
        }
        val callback = object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(connectivityManager.isConnected())
            }

            override fun onLost(network: Network) {
                trySend(connectivityManager.isConnected())
            }

            override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
                trySend(connectivityManager.isConnected())
            }
        }
        val request = Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
        connectivityManager.registerNetworkCallback(request, callback)
        trySend(connectivityManager.isConnected())
        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged().flowOn(ioDispatcher)

    private fun ConnectivityManager.isConnected(): Boolean {
        val network = activeNetwork ?: return false
        val capabilities = getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

}