import androidx.room.gradle.RoomExtension
import com.google.devtools.ksp.gradle.KspExtension
import com.toolkit.plugs.roomVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class RoomPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        apply(plugin = "androidx.room")
        apply(plugin = "com.google.devtools.ksp")

        extensions.configure<KspExtension> {
            arg("room.generateKotlin", "true")
        }

        extensions.configure<RoomExtension> {
            schemaDirectory("$projectDir/schemas")
        }

        dependencies {
            "implementation"("androidx.room:room-runtime:$roomVersion")
            "ksp"("androidx.room:room-compiler:$roomVersion")
            "implementation"("androidx.room:room-ktx:$roomVersion")
        }
    }
}
