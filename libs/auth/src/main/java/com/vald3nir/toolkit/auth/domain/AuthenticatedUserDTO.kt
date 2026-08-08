package com.vald3nir.toolkit.auth.domain

data class AuthenticatedUserDTO(
    val id: Long? = null,
    val uuid: String? = null,
    val name: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
) {
    fun isAuthenticated() = id != null && name != null && email != null
}