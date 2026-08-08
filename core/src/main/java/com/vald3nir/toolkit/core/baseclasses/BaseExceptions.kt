package com.vald3nir.toolkit.core.baseclasses

import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialProviderConfigurationException
import com.vald3nir.toolkit.core.services.analytics.notifyLog

sealed class AppException(message: String) : Exception(message)

class BodyNullException(message: String = "Resposta bem-sucedida, mas corpo nulo") : AppException(message)
class NotFoundException(message: String = "Recurso não encontrado") : AppException(message)
class UnauthorizedException(message: String = "Não autorizado") : AppException(message)
class ForbiddenException(message: String = "Acesso negado") : AppException(message)
class ServerErrorException(message: String = "Erro interno do servidor") : AppException(message)
class BadRequestException(message: String = "Requisição inválida") : AppException(message)
class UnknownAppException(code: Int, message: String) : AppException("Erro desconhecido: $code - $message")

class DefaultException() : AppException("Ocorreu um erro inesperado. Tente novamente mais tarde.")
class UserAuthenticationException() : AppException("Ocorreu um erro ao realizar a autenticação do usuário. Verifique suas credenciais e tente novamente.")
class OfflineException() : AppException("⚠\uFE0F O app não está conectado a Internet. Verifique sua conexão e tente novamente.")
class ParameterInvalidException() : AppException("Ocorreu um erro ao validar os parâmetros da solicitação.")

fun Throwable.treatMessage(showMessage: (String) -> Unit) {
    this.notifyLog()
    if (this is GetCredentialCancellationException || this is GetCredentialProviderConfigurationException) {
        showMessage("Ocorreu um erro ao realizar a autenticação do usuário. Verifique suas credenciais e tente novamente.")
    }
    showMessage(this.message.orEmpty())
}