package com.vald3nir.toolkit.core.services.rest

import android.content.Context
import com.google.gson.GsonBuilder
import com.vald3nir.toolkit.core.BuildConfig
import com.vald3nir.toolkit.core.services.rest.interceptors.ContentTypeInterceptor
import com.vald3nir.toolkit.core.services.rest.interceptors.CurlLoggingInterceptor
import com.vald3nir.toolkit.core.services.rest.interceptors.MockInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkingSetup {

    inline fun <reified T> provideApiService(context: Context, baseURL: String, enableLog: Boolean = false, enableCurl: Boolean = false): T = buildRetrofit(
        context = context, baseURL = baseURL, enableLog = enableLog, enableCurl = enableCurl
    ).create(T::class.java)

    fun buildRetrofit(context: Context, baseURL: String, enableLog: Boolean = false, enableCurl: Boolean = false): Retrofit {
        val gson = GsonBuilder().create()
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .apply {
                listOfNotNull(
                    if (enableLog) loggerInterceptor() else null,
                    ContentTypeInterceptor(),
                    if (enableCurl) CurlLoggingInterceptor() else null,
                    MockInterceptor(context)
                ).forEach(::addInterceptor)
            }
            .build()
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun loggerInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}