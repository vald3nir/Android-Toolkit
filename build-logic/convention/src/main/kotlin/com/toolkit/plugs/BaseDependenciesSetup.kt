package com.toolkit.plugs

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.setupBaseLibs() {
    dependencies {

        // Kotlin and Coroutines
        add("implementation", "org.jetbrains.kotlinx:kotlinx-datetime:$kotlinxDatetime")
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutines")
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinxCoroutines")
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-guava:$kotlinxCoroutines")
        add("implementation", "androidx.concurrent:concurrent-futures-ktx:$concurrent")

        // dataStore
        add("implementation", "androidx.datastore:datastore:$androidxDataStore")
        add("implementation", "androidx.datastore:datastore-core:$androidxDataStore")
        add("implementation", "androidx.datastore:datastore-preferences:$datastorePreferences")

        // AndroidX Core
        add("implementation", "com.google.accompanist:accompanist-permissions:$accompanist")
        add("implementation", "androidx.core:core-splashscreen:$androidxCoreSplashscreen")
        add("implementation", "androidx.browser:browser:$androidxBrowser")
        add("implementation", "androidx.profileinstaller:profileinstaller:$androidxProfileInstaller")
        add("implementation", "androidx.metrics:metrics-performance:$metricsPerformance")
        add("implementation", "androidx.tracing:tracing-ktx:$androidxTracing")
        add("implementation", "androidx.window:window-core:$androidxWindowManager")
        add("implementation", "androidx.activity:activity-ktx:$activityVersion")
        add("implementation", "androidx.core:core-ktx:$coreKtxVersion")
        add("implementation", "androidx.work:work-runtime-ktx:$androidxWork")

        // Lifecycle
        add("implementation", "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
        add("implementation", "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
        add("implementation", "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
        add("implementation", "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
        add("implementation", "androidx.lifecycle:lifecycle-service:$lifecycleVersion")
        add("implementation", "androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")

        // Compose UI
        add("implementation", platform("androidx.compose:compose-bom:$composeBomVersion"))
        add("implementation", "androidx.activity:activity-compose:$activityComposeVersion")
        add("implementation", "androidx.navigation:navigation-compose:$navigationComposeVersion")
        add("implementation", "androidx.compose.ui:ui:$composeUiVersion")
        add("implementation", "androidx.compose.ui:ui-graphics:$composeGraphicsVersion")
        add("implementation", "androidx.compose.ui:ui-tooling-preview:$composeToolingPreviewVersion")
        add("implementation", "androidx.compose.runtime:runtime-livedata:$runtimeLivedataVersion")
        add("implementation", "androidx.compose.runtime:runtime-tracing:$androidxComposeRuntimeTracing")

        // Image Loader
        add("implementation", "io.coil-kt.coil3:coil-compose:$coil")
        add("implementation", "io.coil-kt.coil3:coil-svg:$coil")
        add("implementation", "io.coil-kt.coil3:coil-network-okhttp:$coil")

        // Material Design
        add("implementation", "androidx.compose.material:material:$materialVersion")
        add("implementation", "androidx.compose.material3:material3:$material3Version")
        add("implementation", "androidx.compose.material:material-icons-extended:$materialIconsExtendedVersion")
        add("implementation", "androidx.compose.material3.adaptive:adaptive:$androidxComposeMaterial3Adaptive")
        add("implementation", "androidx.compose.material3.adaptive:adaptive-layout:$androidxComposeMaterial3Adaptive")
        add("implementation", "androidx.compose.material3.adaptive:adaptive-navigation:$androidxComposeMaterial3Adaptive")
        add("implementation", "androidx.compose.material3:material3-adaptive-navigation-suite:1.4.0")
        add("implementation", "androidx.compose.material3:material3-window-size-class:1.4.0")

        // Data Serialization
        add("implementation", "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerialization")
        add("implementation", "com.google.code.gson:gson:$gsonVersion")

        // Test Libs
        add("testImplementation", "org.junit.jupiter:junit-jupiter-api:$junitVersion")
        add("testImplementation", "org.junit.jupiter:junit-jupiter-engine:$junitVersion")
        add("testImplementation", "org.junit.jupiter:junit-jupiter-params:$junitVersion")
        add("testRuntimeOnly", "org.junit.platform:junit-platform-launcher")
        add("testImplementation", "io.mockk:mockk:$mockkVersion")
        add("testImplementation", "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion")
        add("androidTestImplementation", "androidx.test.ext:junit:$androidJunitVersion")
        add("androidTestImplementation", "androidx.test.espresso:espresso-core:$espressoCoreVersion")
        add("debugImplementation", "androidx.compose.ui:ui-test-manifest:$composeUiTestManifestVersion")
        add("debugImplementation", "androidx.compose.ui:ui-tooling:$composeUiToolingVersion")
    }
}
