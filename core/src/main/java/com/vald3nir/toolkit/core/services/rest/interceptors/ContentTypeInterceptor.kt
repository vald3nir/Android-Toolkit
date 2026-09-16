package com.vald3nir.toolkit.core.services.rest.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ContentTypeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(modifiedRequest)
    }
}