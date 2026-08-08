package com.vald3nir.toolkit.core.services.analytics

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics

fun Exception.notifyLog() {
    this.printStackTrace()
    Firebase.crashlytics.recordException(this)
}

fun Throwable.notifyLog() {
    this.printStackTrace()
    Firebase.crashlytics.recordException(this)
}