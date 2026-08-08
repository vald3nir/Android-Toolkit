package com.vald3nir.toolkit.core.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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