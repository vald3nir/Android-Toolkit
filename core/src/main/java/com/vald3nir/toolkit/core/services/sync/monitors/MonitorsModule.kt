package com.vald3nir.toolkit.core.services.sync.monitors

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MonitorsModule {

    @Binds
    internal abstract fun bindsNetworkMonitor(impl: NetworkMonitorImpl): NetworkMonitor

    @Binds
    internal abstract fun bindsTimeZoneMonitor(impl: TimeZoneMonitorImpl): TimeZoneMonitor
}