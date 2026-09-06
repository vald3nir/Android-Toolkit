package com.vald3nir.toolkit.core.baseclasses

import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vald3nir.toolkit.core.services.sync.monitors.NetworkMonitor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class BaseViewModelParameters @Inject constructor(
    @ApplicationContext val context: Context,
    val networkMonitor: NetworkMonitor,
    val messageNotifier: MessageNotifier,
    val navigationDelegate: NavigationDelegate,
)

abstract class BaseViewModel(private val parameters: BaseViewModelParameters) : ViewModel() {

    val hasInternetConnection: StateFlow<Boolean> = parameters.networkMonitor.isOnline
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = true
        )

    fun notifyUiMessage(message: String) {
        viewModelScope.launch {
            parameters.messageNotifier.showMessage(message)
        }
    }

    fun notifyUiMessage(@StringRes messageId: Int) {
        notifyUiMessage(parameters.context.getString(messageId))
    }

    fun messageObserver() = parameters.messageNotifier.messages

    fun navigateObserver() = parameters.navigationDelegate.backEvents

    fun navigateBack() {
        parameters.navigationDelegate.navigateBack()
    }

    private val _uiState = MutableStateFlow<BaseUiState>(BaseUiState.LoadingState(false))
    val uiState: StateFlow<BaseUiState> = _uiState.asStateFlow()

    fun notifyState(state: BaseUiState) {
        _uiState.value = state
    }

    fun <T> ViewModel.safeLaunch(
        action: suspend () -> T,
        onSuccessEvent: (T) -> Unit = {},
        onFailureEvent: (Throwable) -> Unit = {}
    ): Job {
        return viewModelScope.launch {
            runCatching {
                action()
            }.onSuccess { response ->
                onSuccessEvent(response)
            }.onFailure { error ->
                onFailureEvent(error)
                error.treatMessage { notifyUiMessage(it) }
            }
        }
    }
}