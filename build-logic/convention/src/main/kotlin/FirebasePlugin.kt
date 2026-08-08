import com.toolkit.plugs.credentialsVersion
import com.toolkit.plugs.firebaseBom
import com.toolkit.plugs.googleIDCredentialsVersion
import com.toolkit.plugs.googlePlayServiceGMSVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FirebasePlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        dependencies {
            // Firebase Libs
            "implementation"(platform("com.google.firebase:firebase-bom:$firebaseBom"))
            "implementation"("com.google.firebase:firebase-storage")
            "implementation"("com.google.firebase:firebase-database")
            "implementation"("com.google.firebase:firebase-auth")
            "implementation"("com.google.firebase:firebase-crashlytics")
            "implementation"("com.google.firebase:firebase-analytics")

            // Firebase Auth Credentials
            "implementation"("androidx.credentials:credentials:$credentialsVersion")
            "implementation"("androidx.credentials:credentials-play-services-auth:$credentialsVersion")
            "implementation"("com.google.android.libraries.identity.googleid:googleid:${googleIDCredentialsVersion}")

            // Google Login
            "implementation"("com.google.android.gms:play-services-auth:$googlePlayServiceGMSVersion")
        }
    }
}