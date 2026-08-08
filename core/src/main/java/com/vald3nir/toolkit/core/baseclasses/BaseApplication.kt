package com.vald3nir.toolkit.core.baseclasses

import android.app.Application
import android.content.pm.ApplicationInfo
import android.os.StrictMode

open class BaseApplication : Application() {

    private fun isDebuggable() = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE

    protected fun setStrictModePolicy() {
        if (isDebuggable()) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build(),
            )
        }
    }
}