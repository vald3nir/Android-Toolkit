import com.toolkit.plugs.hilt
import com.toolkit.plugs.hiltExt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        apply(plugin = "com.google.devtools.ksp")

        dependencies {
            add("ksp", "com.google.dagger:hilt-compiler:$hilt")
            add("ksp", "androidx.hilt:hilt-compiler:$hiltExt")
            add("implementation", "androidx.hilt:hilt-work:$hiltExt")
            add("implementation", "androidx.hilt:hilt-navigation-compose:$hiltExt")
        }

        pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
            dependencies {
                add("implementation", "com.google.dagger:hilt-core:$hilt")
            }
        }

        pluginManager.withPlugin("com.android.base") {
            apply(plugin = "dagger.hilt.android.plugin")
            dependencies {
                add("implementation", "com.google.dagger:hilt-android:$hilt")
            }
        }
    }
}