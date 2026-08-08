package com.toolkit.plugs

import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File
import java.util.Properties

fun Project.setupBuildTypes() {
    extensions.configure<ApplicationExtension> {
        buildTypes.apply {
            maybeCreate(AppBuildType.DEBUG.lowerCaseName)
            getByName(AppBuildType.DEBUG.lowerCaseName) {
                applicationIdSuffix = AppBuildType.DEBUG.applicationIdSuffix
                isMinifyEnabled = false
                isDebuggable = true
            }
            maybeCreate(AppBuildType.RELEASE.lowerCaseName)
            getByName(AppBuildType.RELEASE.lowerCaseName) {
                isMinifyEnabled = true
                isDebuggable = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
    }
}

fun Project.setupProductFlavors() {
    extensions.configure<ApplicationExtension> {
        flavorDimensions += "environment"
        productFlavors {
            maybeCreate("dev").apply {
                dimension = "environment"
                applicationIdSuffix = ".dev"
                versionNameSuffix = "-dev"
            }
            maybeCreate("prod").apply {
                dimension = "environment"
            }
        }
    }
}

fun Project.setupSigningConfigs(parameters: AppEnvironmentParameters) {
    extensions.configure<ApplicationExtension> {
        buildTypes.apply {
            signingConfigs {
                getByName(AppBuildType.DEBUG.lowerCaseName) {
                    inputParam(parameters = parameters)
                }
                create(AppBuildType.RELEASE.lowerCaseName) {
                    inputParam(parameters = parameters)
                }
            }
        }
    }
}

private fun ApkSigningConfig.inputParam(parameters: AppEnvironmentParameters) {
    this.keyAlias = parameters.keyAlias
    this.keyPassword = parameters.keyPassword
    this.storeFile = File(parameters.pathKeyStore)
    this.storePassword = parameters.storePassword
}

data class AppEnvironmentParameters(
    val pathKeyStore: String = "",
    val keyAlias: String = "",
    val keyPassword: String = "",
    val storePassword: String = "",
    val appPrivacyPolicyURL: String = "",
    val termsUseURL: String = "",
    val webGoogleClientID: String = "",
    val firebaseServiceCredentialsFilePath: String = "",
    val supabaseUrl: String = "",
    val supabaseKey: String = "",
) {
    companion object {

        fun from(envFilePath: String): AppEnvironmentParameters {
            val envFile = File(envFilePath)
            if (!envFile.exists()) return AppEnvironmentParameters()
            val props = Properties().apply {
                envFile.inputStream().use { load(it) }
            }
            return AppEnvironmentParameters(
                pathKeyStore = props.getProperty("KEY_STORE_PATH", ""),
                keyAlias = props.getProperty("KEY_STORE_ALIAS", ""),
                keyPassword = props.getProperty("KEY_STORE_PASSWORD", ""),
                storePassword = props.getProperty("STORE_PASSWORD", ""),
                appPrivacyPolicyURL = props.getProperty("APP_PRIVACY_POLICY_URL", ""),
                termsUseURL = props.getProperty("APP_TERMS_USE_URL", ""),
                webGoogleClientID = props.getProperty("WEB_GOOGLE_CLIENT_ID", ""),
                firebaseServiceCredentialsFilePath = props.getProperty("FIREBASE_SERVICE_CREDENTIALS_FILE_PATH", ""),
                supabaseUrl = props.getProperty("SUPABASE_URL", ""),
                supabaseKey = props.getProperty("SUPABASE_KEY", ""),
            )
        }
    }
}