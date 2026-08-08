import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.vald3nir.toolkit.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("ApplicationPlugin") {
            id = libs.plugins.toolkit.application.get().pluginId
            implementationClass = "ApplicationPlugin"
        }
        register("ModulePlugin") {
            id = libs.plugins.toolkit.module.get().pluginId
            implementationClass = "ModulePlugin"
        }
        register("HiltPlugin") {
            id = libs.plugins.toolkit.di.hilt.get().pluginId
            implementationClass = "HiltPlugin"
        }
        register("RoomPlugin") {
            id = libs.plugins.toolkit.room.get().pluginId
            implementationClass = "RoomPlugin"
        }
        register("NetworkPlugin") {
            id = libs.plugins.toolkit.network.get().pluginId
            implementationClass = "NetworkPlugin"
        }
        register("FirebasePlugin") {
            id = libs.plugins.toolkit.firebase.get().pluginId
            implementationClass = "FirebasePlugin"
        }
        register("SupabasePlugin") {
            id = libs.plugins.toolkit.supabase.get().pluginId
            implementationClass = "SupabasePlugin"
        }
    }
}