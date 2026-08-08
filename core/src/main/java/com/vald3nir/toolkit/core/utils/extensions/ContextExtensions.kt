package com.vald3nir.toolkit.core.utils.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.provider.Settings
import com.vald3nir.toolkit.core.services.analytics.notifyLog

private fun Context.getLocalPreferences(): SharedPreferences {
    return this.getSharedPreferences("Local-Preferences", Context.MODE_PRIVATE)
}

fun Context.finishAffinity() {
    (this as? Activity)?.finishAffinity()
}

private fun Context.getLocalPreferencesEditor(): SharedPreferences.Editor {
    return this.getLocalPreferences().edit()
}

fun Context.saveDataString(key: String, data: String?) {
    val sharedPref = getLocalPreferencesEditor()
    sharedPref.putString(key, data)
    sharedPref.apply()
}

fun Context.loadDataString(key: String): String? {
    val sharedPref = getLocalPreferences()
    return sharedPref.getString(key, null)
}

fun Context.openLinkURL(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

fun Context.openWifiSettings() {
    try {
        startActivity(Intent(Settings.Panel.ACTION_WIFI))
    } catch (e: ActivityNotFoundException) {
        e.notifyLog()
        startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
    }
}