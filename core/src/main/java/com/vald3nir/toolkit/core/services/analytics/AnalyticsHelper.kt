package com.vald3nir.toolkit.core.services.analytics

interface AnalyticsHelper {
    fun logEvent(event: AnalyticsEvent)
    fun onLog(message: String)
}