package com.vald3nir.toolkit.core.theme.repository

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ThemeRepository {
    @Binds
    internal abstract fun bindsThemaRepository(impl: ThemaRepositoryImpl): ThemaRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal object ThemeDataSources {
    @Provides
    @Singleton
    fun bindsThemaDataSource(@ApplicationContext context: Context) = ThemaDataSource(context)
}