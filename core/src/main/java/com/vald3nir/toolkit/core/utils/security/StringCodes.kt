package com.vald3nir.toolkit.core.utils.security

import java.security.MessageDigest
import java.util.UUID

fun generateUUID(): String {
    return UUID.randomUUID().toString()
}

fun String?.emailToCode(): String {
    return this?.replace("@", "_")?.replace(".", "_").orEmpty()
}

fun UUID.toSha256Hash(): String {
    val digest = MessageDigest.getInstance("SHA-256").digest(this.toString().toByteArray())
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}