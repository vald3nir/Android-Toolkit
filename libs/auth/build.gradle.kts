plugins {
    alias(libs.plugins.toolkit.module)
    alias(libs.plugins.toolkit.di.hilt)
    alias(libs.plugins.toolkit.firebase)
}

android {
    namespace = "com.vald3nir.toolkit.auth"
}

dependencies {
    implementation(project(":toolkit:core"))
}