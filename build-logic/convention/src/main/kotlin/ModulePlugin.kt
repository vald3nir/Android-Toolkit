import com.android.build.api.dsl.LibraryExtension
import com.toolkit.plugs.EnvironmentSetup
import com.toolkit.plugs.configureKotlinAndroid
import com.toolkit.plugs.setupBaseLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ModulePlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        with(pluginManager) {
            apply("com.android.library")
            apply("kotlin-parcelize")
            apply("org.jetbrains.kotlin.plugin.serialization")
            apply("com.google.devtools.ksp")
            apply("org.jetbrains.kotlin.plugin.compose")
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            defaultConfig.apply {
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

        setupBaseLibs()
    }
}