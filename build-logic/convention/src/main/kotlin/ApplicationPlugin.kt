import com.android.build.api.dsl.ApplicationExtension
import com.toolkit.plugs.EnvironmentSetup
import com.toolkit.plugs.configureKotlinAndroid
import com.toolkit.plugs.setupBaseLibs
import com.toolkit.plugs.setupBuildTypes
import com.toolkit.plugs.setupProductFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        with(pluginManager) {
            apply("com.android.application")
            apply("kotlin-parcelize")
            apply("org.jetbrains.kotlin.plugin.serialization")
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        extensions.configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            defaultConfig.apply {
                targetSdk = EnvironmentSetup.TARGET_SDK
                vectorDrawables {
                    useSupportLibrary = true
                }
            }
            buildFeatures {
                compose = true
                buildConfig = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = EnvironmentSetup.KOTLIN_COMPILER
            }
            packaging {
                resources {
                    excludes += "META-INF/*"
                }
            }
        }
        setupBuildTypes()
        setupProductFlavors()
        setupBaseLibs()
    }
}