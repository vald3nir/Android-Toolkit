package com.toolkit.plugs

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal fun Project.configureKotlinAndroid(commonExtension: ApplicationExtension) {
    commonExtension.apply {
        compileSdk = EnvironmentSetup.COMPILE_SDK
        defaultConfig {
            minSdk = EnvironmentSetup.MIN_SDK
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        compileOptions {
            sourceCompatibility = EnvironmentSetup.JAVA_VERSION
            targetCompatibility = EnvironmentSetup.JAVA_VERSION
            isCoreLibraryDesugaringEnabled = true
        }
    }
    configureKotlin<KotlinAndroidProjectExtension>()

    dependencies {
        add("coreLibraryDesugaring", "com.android.tools:desugar_jdk_libs:$androidDesugarJdkLibs")
    }
}

internal fun Project.configureKotlinAndroid(commonExtension: LibraryExtension) {
    commonExtension.apply {
        compileSdk = EnvironmentSetup.COMPILE_SDK
        defaultConfig {
            minSdk = EnvironmentSetup.MIN_SDK
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        compileOptions {
            sourceCompatibility = EnvironmentSetup.JAVA_VERSION
            targetCompatibility = EnvironmentSetup.JAVA_VERSION
            isCoreLibraryDesugaringEnabled = true
        }
    }
    configureKotlin<KotlinAndroidProjectExtension>()

    dependencies {
        add("coreLibraryDesugaring", "com.android.tools:desugar_jdk_libs:$androidDesugarJdkLibs")
    }
}

private inline fun <reified T : KotlinBaseExtension> Project.configureKotlin() = configure<T> {
    val warningsAsErrors = providers.gradleProperty("warningsAsErrors").map { it.toBoolean() }.orElse(false)
    when (this) {
        is KotlinAndroidProjectExtension -> compilerOptions
        is KotlinJvmProjectExtension -> compilerOptions
        else -> TODO("Unsupported project extension $this ${T::class}")
    }.apply {
        jvmTarget = JvmTarget.JVM_11
        allWarningsAsErrors = warningsAsErrors
        freeCompilerArgs.add(
            // Enable experimental coroutines APIs, including Flow
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
        freeCompilerArgs.add(
            "-Xconsistent-data-class-copy-visibility"
        )
    }
}