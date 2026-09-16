package com.vald3nir.toolkit.core.services.rest.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer

class CurlLoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val curlCommand = buildCurlCommand(request)
        println("\n-------------------------------------------------------------------------------")
        println("CURL: $curlCommand")
        println("-------------------------------------------------------------------------------\n")
        return chain.proceed(request)
    }

    private fun buildCurlCommand(request: Request): String {
        val method = request.method.uppercase()
        val headers = request.headers
        val url = request.url

        val curlBuilder = StringBuilder("curl --location --request $method '$url'")
        headers.forEach { it ->
            val name = it.first
            val value = it.second
            curlBuilder.append(" \\\n--header '$name: $value'")
        }

        request.body?.let { body ->
            val buffer = Buffer()
            body.writeTo(buffer)
            val bodyString = buffer.readUtf8()
            curlBuilder.append(" \\\n--data '$bodyString'")
        }

        return curlBuilder.toString()
    }
}