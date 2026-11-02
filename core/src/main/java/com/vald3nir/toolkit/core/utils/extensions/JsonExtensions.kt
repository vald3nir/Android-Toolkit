package com.vald3nir.toolkit.core.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Any.toJson() = Gson().toJson(this)

fun <T> String.fromJson(kClass: Class<T>): T = Gson().fromJson(this, kClass)

inline fun <reified T> fromJsonToObject(json: String): T {
    val type = object : TypeToken<T>() {}.type
    return Gson().fromJson(json, type)
}

inline fun <reified T> fromJsonToList(json: String): List<T> {
    val type = object : TypeToken<List<T>>() {}.type
    return Gson().fromJson(json, type)
}

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: LocalDateTime) = encoder.encodeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
    override fun deserialize(decoder: Decoder): LocalDateTime = LocalDateTime.parse(decoder.decodeString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}