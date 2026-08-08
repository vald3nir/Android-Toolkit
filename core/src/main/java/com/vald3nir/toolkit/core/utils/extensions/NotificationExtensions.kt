package com.vald3nir.toolkit.core.utils.extensions

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.vald3nir.toolkit.core.R

fun Context.buildNotification(
    channelId: String,
    channelName: String,
    channelDescription: String,
    notificationTitle: String,
    @DrawableRes icon: Int
): Notification {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = channelDescription
        }
        val notificationManager: NotificationManager? = getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        notificationManager?.createNotificationChannel(channel)
    }
    return NotificationCompat.Builder(this, channelId)
        .setSmallIcon(icon)
        .setContentTitle(notificationTitle)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()
}

fun Context.defaultSyncWorkNotification(appName: String) = buildNotification(
    channelId = "SyncNotificationChannel",
    channelName = getString(R.string.default_sync_work_notification_channel_name),
    channelDescription = getString(R.string.default_sync_work_notification_channel_description),
    notificationTitle = appName,
    icon = R.drawable.ic_notification
)