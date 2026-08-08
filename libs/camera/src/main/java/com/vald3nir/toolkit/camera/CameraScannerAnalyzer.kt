package com.vald3nir.toolkit.camera

import androidx.camera.core.ImageAnalysis

abstract class CameraScannerAnalyzer(
    private val onCodeDetected: (String?) -> Unit
) : ImageAnalysis.Analyzer