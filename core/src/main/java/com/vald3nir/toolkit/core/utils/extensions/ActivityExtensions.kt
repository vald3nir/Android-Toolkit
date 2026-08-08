package com.vald3nir.toolkit.core.utils.extensions

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.compose.runtime.compositionLocalOf
import androidx.core.util.Consumer
import androidx.core.view.WindowInsetsControllerCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.datetime.TimeZone

fun Activity.updateStatusBarColor() {
    updateStatusBarColor(if (resources.configuration.isSystemInDarkTheme) Color.BLACK else Color.WHITE)
}

fun Activity.updateStatusBarColor(statusBarColor: Int) {
    // WindowCompat.setDecorFitsSystemWindows(window, false) // BUG: for some devices it creates extra spacing, for others it removes it.
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = false

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) { // Android 15+
        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
            view.setBackgroundColor(statusBarColor)
            // Adjust padding to avoid overlap
            view.setPadding(0, statusBarInsets.top, 0, 0)
            insets
        }
    } else {
        window.statusBarColor = statusBarColor
        window.navigationBarColor = statusBarColor
    }
}

val LocalTimeZone = compositionLocalOf { TimeZone.currentSystemDefault() }

val Configuration.isSystemInDarkTheme
    get() = (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

fun ComponentActivity.isSystemInDarkTheme() = callbackFlow {
    channel.trySend(resources.configuration.isSystemInDarkTheme)
    val listener = Consumer<Configuration> {
        channel.trySend(it.isSystemInDarkTheme)
    }
    addOnConfigurationChangedListener(listener)
    awaitClose { removeOnConfigurationChangedListener(listener) }
}.distinctUntilChanged().conflate()


