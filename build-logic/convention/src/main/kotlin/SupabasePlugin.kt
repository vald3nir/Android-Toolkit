import com.toolkit.plugs.ktor
import com.toolkit.plugs.supabaseBom
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SupabasePlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        dependencies {

            "implementation"(platform("io.github.jan-tennert.supabase:bom:$supabaseBom"))
            "implementation"("io.github.jan-tennert.supabase:postgrest-kt")
            "implementation"("io.github.jan-tennert.supabase:auth-kt")
            "implementation"("io.github.jan-tennert.supabase:storage-kt")

            "implementation"("io.ktor:ktor-client-android:$ktor")
            "implementation"("io.ktor:ktor-client-core:$ktor")
            "implementation"("io.ktor:ktor-utils:$ktor")
        }
    }
}