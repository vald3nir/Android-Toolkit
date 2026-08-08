package com.vald3nir.toolkit.core.services.rest

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkingSetup {

    private val baseInterceptors = listOf(
        loggerInterceptor(),
        ContentTypeInterceptor(),
        CurlLoggingInterceptor()
    )

    fun buildOkHttpClient(interceptors: List<Interceptor> = baseInterceptors): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        interceptors.forEach { interceptor ->
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    fun buildRetrofit(baseURL: String): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(buildOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    inline fun <reified T> provideApiService(baseURL: String): T {
        return buildRetrofit(baseURL).create(T::class.java)
    }
}