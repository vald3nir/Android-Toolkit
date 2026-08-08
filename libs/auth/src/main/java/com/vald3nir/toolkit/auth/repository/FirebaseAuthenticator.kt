package com.vald3nir.toolkit.auth.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.vald3nir.toolkit.auth.domain.AuthenticatedUserDTO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.tasks.await

object FirebaseAuthenticator {

    fun disconnect() {
        Firebase.auth.signOut()
    }

    fun getFirebaseUser() = Firebase.auth.currentUser?.let {
        AuthenticatedUserDTO(
            uuid = it.uid,
            name = it.displayName,
            email = it.email,
            photoUrl = it.photoUrl?.toString()
        )
    }

    fun isUserLogged(): Boolean = getFirebaseUser() != null

    fun observeUserLogged(): Flow<Boolean> = callbackFlow {
        val auth = Firebase.auth
        val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            trySend(firebaseAuth.currentUser != null)
        }
        trySend(auth.currentUser != null)
        auth.addAuthStateListener(listener)
        awaitClose {
            auth.removeAuthStateListener(listener)
        }
    }.distinctUntilChanged()

    suspend fun authenticate(googleIdToken: String): AuthenticatedUserDTO? {
        val credential = GoogleAuthProvider.getCredential(googleIdToken, null)
        Firebase.auth.signInWithCredential(credential).await()
        return getFirebaseUser()
    }
}