plugins {
    alias(libs.plugins.toolkit.module)
    alias(libs.plugins.toolkit.di.hilt)
}
android {
    namespace = "com.vald3nir.toolkit.servicelocation"
}

dependencies {
    implementation(libs.play.services.location)
}