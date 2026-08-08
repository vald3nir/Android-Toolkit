package com.vald3nir.toolkit.designsystem.components.notifications

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(resultado: String?) {
    val context = LocalContext.current
    LaunchedEffect(resultado) {
        if (!resultado.isNullOrBlank()) {
            Toast.makeText(context, resultado, Toast.LENGTH_LONG).show()
        }
    }
}