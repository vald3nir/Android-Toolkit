package com.vald3nir.toolkit.core.utils.security

import java.security.MessageDigest
import java.util.UUID

fun generateUUID(): String = UUID.randomUUID().toString()

fun String?.orNewUUID(): String = this ?: generateUUID()

fun String?.emailToCode(): String = this
    ?.replace("@", "_")
    ?.replace(".", "_")
    .orEmpty()

fun UUID.toSha256Hash(): String {
    val digest = MessageDigest.getInstance("SHA-256").digest(toString().toByteArray())
    return digest.joinToString("") { "%02x".format(it) }
}