package com.vald3nir.toolkit.servicelocation

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationService : LifecycleService() {

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private val notificationId = 1
    private val channelId = "LocationServiceChannel"

    override fun onCreate() {
        super.onCreate()
        startForeground(notificationId, createNotification())
        startLocationUpdates()
    }

    private fun createNotification(): Notification {
        val channel = NotificationChannel(
            channelId, "Location Service",
            NotificationManager.IMPORTANCE_LOW
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Rastreamento de Localização")
            .setContentText("Monitorando a localização em segundo plano")
//            .setSmallIcon(R.drawable.ic_location)
            .build()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 60000  // Atualiza a cada 60 segundos
            fastestInterval = 30000
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { location ->
                sendLocationToApi(location.latitude, location.longitude)
            }
        }
    }

    private fun sendLocationToApi(lat: Double, lon: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //val response = ApiService.sendLocation(lat, lon)
                //Log.d("LocationService", "Localização enviada: $response")
            } catch (e: Exception) {
                Log.e("LocationService", "Erro ao enviar localização", e)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}