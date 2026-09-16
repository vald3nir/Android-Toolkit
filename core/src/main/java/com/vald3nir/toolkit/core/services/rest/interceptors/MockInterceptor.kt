package com.vald3nir.toolkit.core.services.rest.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Invocation
import java.net.SocketTimeoutException

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class MockParam(
    val path: String,
    val load: Boolean = false,
    val connectionDelay: Long = 0,
)

class MockInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().tag(Invocation::class.java)?.method()?.annotations?.let { annotations ->
            annotations.forEach { annotation ->
                when (annotation.annotationClass) {
                    MockParam::class -> {
                        (annotation as MockParam).apply {
                            if (!load) return chain.proceed(chain.request())
                            Thread.sleep(connectionDelay)
                            if (connectionDelay / 1000 > DEFAULT_TIME_OUT_VALUE) throw SocketTimeoutException()
                            val response = readFile(path)
                            return Response.Builder()
                                .code(SUCCESS_STATUS_CODE)
                                .body(response.toByteArray().toResponseBody(CONTENT_TYPE.toMediaTypeOrNull()))
                                .protocol(Protocol.HTTP_2)
                                .message(response)
                                .request(chain.request())
                                .addHeader(CONTENT_TYPE_NAME, CONTENT_TYPE)
                                .build()
                        }
                    }
                }
            }
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun readFile(fileName: String): String = context.assets.open(fileName).use { it.readBytes().toString(Charsets.UTF_8) }

    private companion object {
        private const val DEFAULT_TIME_OUT_VALUE = 60L
        private const val SUCCESS_STATUS_CODE = 200
        private const val CONTENT_TYPE_NAME = "content-type"
        private const val CONTENT_TYPE = "application/json"
    }
}