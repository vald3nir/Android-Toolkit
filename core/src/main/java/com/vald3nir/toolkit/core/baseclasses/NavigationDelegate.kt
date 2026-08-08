package com.vald3nir.toolkit.core.baseclasses

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDelegate @Inject constructor() {
    private val _backEvents = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val backEvents = _backEvents.asSharedFlow()

    fun navigateBack() {
        _backEvents.tryEmit(Unit)
    }
}