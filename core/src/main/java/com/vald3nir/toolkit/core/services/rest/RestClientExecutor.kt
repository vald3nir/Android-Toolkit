package com.vald3nir.toolkit.core.services.rest

import com.google.gson.Gson
import com.vald3nir.toolkit.core.baseclasses.DefaultException
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

data class ErrorDTO(val message: String)

open class RestClientExecutor {

    companion object {
        private val gson = Gson()
    }

    suspend inline fun <reified T> execute(crossinline call: suspend () -> T): T = runCatching { call() }.onFailure { error ->
        when (error) {
            is HttpException -> {
                val statusCode = error.code()
                val apiMessage = parseErrorBody(error.response()?.errorBody())
                throw Exception(mapToFriendlyMessage(statusCode, apiMessage))
            }
            is IOException -> {
                // Falha de conexão, timeout, etc.
                throw Exception("Problema de conexão. Verifique sua internet e tente novamente.")
            }
            is Exception -> {
                error.printStackTrace()
                throw DefaultException()
            }
        }
    }.getOrThrow()

    fun parseErrorBody(errorBody: ResponseBody?): String? {
        return try {
            errorBody?.charStream()?.use {
                gson.fromJson(it, ErrorDTO::class.java)
            }?.message
        } catch (e: Exception) {
            null
        }
    }

    fun mapToFriendlyMessage(statusCode: Int, apiMessage: String?): String {
        return when (statusCode) {
            400 -> apiMessage ?: "Requisição inválida. Verifique os dados enviados."
            401 -> "Sessão expirada. Faça login novamente."
            403 -> "Você não tem permissão para realizar esta ação."
            404 -> "Recurso não encontrado."
            500 -> "Erro interno no servidor. Tente novamente mais tarde."
            else -> apiMessage ?: "Ocorreu um erro inesperado. Tente novamente mais tarde."
        }
    }
}