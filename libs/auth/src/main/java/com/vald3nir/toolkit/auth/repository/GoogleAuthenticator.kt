package com.vald3nir.toolkit.auth.repository

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.vald3nir.toolkit.core.utils.security.toSha256Hash
import java.util.UUID

object GoogleAuthenticator {

    suspend fun authenticate(context: Context, webGoogleClientID: String, uuid: UUID): String {
        val credentialManager = CredentialManager.create(context)
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(webGoogleClientID)
            .setNonce(uuid.toSha256Hash()) // Hashed nonce to be passed to Google sign-in
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        val result = credentialManager.getCredential(request = request, context = context)
        return GoogleIdTokenCredential.createFrom(result.credential.data).idToken
    }
}