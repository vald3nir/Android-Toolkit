package com.toolkit.plugs

enum class AppBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE;

    val lowerCaseName: String
        get() = name.lowercase()
}
