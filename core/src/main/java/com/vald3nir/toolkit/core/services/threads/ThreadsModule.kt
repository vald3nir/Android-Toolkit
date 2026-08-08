package com.vald3nir.toolkit.core.services.threads

import android.app.Activity
import android.util.Log
import android.view.Window
import androidx.metrics.performance.JankStats
import androidx.metrics.performance.JankStats.OnFrameListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThreadsModule {

    @Provides
    @Dispatcher(JobScope.IO)
    fun providesScopeIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(JobScope.Default)
    fun providesScopeDefault(): CoroutineDispatcher = Dispatchers.Default
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineScopesModule {

    @Provides
    @Singleton
    @ApplicationScope
    fun providesCoroutineScope(@Dispatcher(JobScope.Default) dispatcher: CoroutineDispatcher) = CoroutineScope(SupervisorJob() + dispatcher)
}

@Module
@InstallIn(ActivityComponent::class)
object JankStatsModule {

    @Provides
    fun providesOnFrameListener() = OnFrameListener { frameData ->
        if (frameData.isJank) {
            Log.v("NiA Jank", frameData.toString())
        }
    }

    @Provides
    fun providesWindow(activity: Activity): Window = activity.window

    @Provides
    fun providesJankStats(window: Window, frameListener: OnFrameListener) = JankStats.createAndTrack(window, frameListener)
}