plugins {
    alias(libs.plugins.toolkit.module)
}

android {
    namespace = "com.vald3nir.toolkit.camera"
}

dependencies {
    // Modules
    implementation(project(":toolkit:core"))
    // CameraX
    val cameraxVersion = "1.6.1"
    api("androidx.camera:camera-camera2:$cameraxVersion")
    api("androidx.camera:camera-lifecycle:$cameraxVersion")
    api("androidx.camera:camera-view:$cameraxVersion")
    // ML Kit Barcode Scanning (Para ler o QR Code)
    api("com.google.mlkit:barcode-scanning:17.3.0")
    // Acompanhamento de permissões amigável no Compose
    api("com.google.accompanist:accompanist-permissions:0.37.3")
}