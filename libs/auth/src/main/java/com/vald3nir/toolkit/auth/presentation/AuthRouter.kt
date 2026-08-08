package com.vald3nir.toolkit.auth.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
private data object FirebaseAuthRoute

fun NavController.navigateToAuth() {
    this.navigate(FirebaseAuthRoute)
}

fun NavGraphBuilder.setupAuthRoute(
    appPrivacyPolicyURL: String,
    appTermsUseLink: String,
    webGoogleClientID: String,
) {
    composable<FirebaseAuthRoute> {
        AuthScreen(
            appPrivacyPolicyURL = appPrivacyPolicyURL,
            appTermsUseLink = appTermsUseLink,
            webGoogleClientID = webGoogleClientID,
        )
    }
}