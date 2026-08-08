package com.vald3nir.toolkit.core.services.threads

import android.util.Log
import androidx.concurrent.futures.await
import androidx.profileinstaller.ProfileVerifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileVerifierLogger @Inject constructor(@ApplicationScope private val scope: CoroutineScope) {

    companion object {
        private const val TAG = "ProfileInstaller"
    }

    operator fun invoke() = scope.launch {
        val status = ProfileVerifier.getCompilationStatusAsync().await()
        Log.d(TAG, "Status code: ${status.profileInstallResultCode}")
        val message = when {
            status.isCompiledWithProfile -> "App compiled with profile"
            status.hasProfileEnqueuedForCompilation() -> "Profile enqueued for compilation"
            else -> "Profile not compiled nor enqueued"
        }
        Log.d(TAG, message)
    }
}