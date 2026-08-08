import com.toolkit.plugs.hivemqMqttClient
import com.toolkit.plugs.okhttp
import com.toolkit.plugs.retrofit
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class NetworkPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        dependencies {
            "implementation"("com.squareup.okhttp3:logging-interceptor:$okhttp")
            "implementation"("com.squareup.retrofit2:retrofit:$retrofit")
            "implementation"("com.squareup.retrofit2:converter-gson:$retrofit")
            "implementation"("com.squareup.retrofit2:converter-kotlinx-serialization:$retrofit")
            // MQTT Client
            "implementation"("com.hivemq:hivemq-mqtt-client:$hivemqMqttClient")
        }
    }
}