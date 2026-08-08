package com.vald3nir.toolkit.core.baseclasses

sealed interface BaseUiState {
    data object EmptySate : BaseUiState
    data class ShowState(val data: Any? = null) : BaseUiState
    data class LoadingState(val show: Boolean = true) : BaseUiState
    data class ErrorState(val message: String) : BaseUiState
    data object OffLineState : BaseUiState
    data object FinishState : BaseUiState
}