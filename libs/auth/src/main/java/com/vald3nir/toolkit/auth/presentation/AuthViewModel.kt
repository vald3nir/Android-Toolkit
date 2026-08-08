package com.vald3nir.toolkit.auth.presentation

import android.content.Context
import com.vald3nir.toolkit.auth.repository.AuthenticatedUserRepository
import com.vald3nir.toolkit.auth.repository.FirebaseAuthenticator
import com.vald3nir.toolkit.auth.repository.GoogleAuthenticator
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.baseclasses.BaseViewModel
import com.vald3nir.toolkit.core.baseclasses.BaseViewModelParameters
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthenticatedUserRepository,
    parameters: BaseViewModelParameters
) : BaseViewModel(parameters) {

    fun signInWithGoogle(context: Context, webGoogleClientID: String) {
        safeLaunch(
            action = {
                notifyState(BaseUiState.LoadingState(true))
                val rawNonce = UUID.randomUUID()
                val googleIdToken = GoogleAuthenticator.authenticate(context, webGoogleClientID, rawNonce)
                val authenticatedUser = FirebaseAuthenticator.authenticate(googleIdToken)
                repository.onAuthenticateWithGoogle(googleIdToken = googleIdToken, uuid = rawNonce)
                repository.updateAuthenticatedUser(authenticatedUser)
            },
            onSuccessEvent = {
                notifyState(BaseUiState.FinishState)
            },
            onFailureEvent = {
                notifyState(BaseUiState.LoadingState(false))
            }
        )
    }
}