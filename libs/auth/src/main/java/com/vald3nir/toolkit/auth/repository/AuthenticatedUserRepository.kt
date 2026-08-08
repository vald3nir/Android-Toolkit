package com.vald3nir.toolkit.auth.repository

import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface AuthenticatedUserRepository {
    suspend fun updateAuthenticatedUser(authenticatedUser: AuthenticatedUserDTO?)
    suspend fun onAuthenticateWithGoogle(googleIdToken: String, uuid: UUID) = Unit
    fun loadAuthenticatedUser(): Flow<AuthenticatedUserDTO>
    suspend fun logout()
}