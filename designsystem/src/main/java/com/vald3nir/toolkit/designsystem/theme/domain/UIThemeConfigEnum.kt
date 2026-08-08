package com.vald3nir.toolkit.designsystem.theme.domain

enum class UIThemeConfigEnum(private val label: String) {
    LIGHT("Claro"),
    DARK("Escuro"),
    FOLLOW_SYSTEM("Sistema");

    val key: String
        get() = label

    companion object {

        fun fromKey(key: String?): UIThemeConfigEnum? {
            return key
                ?.let { value -> entries.firstOrNull { it.label.equals(value, ignoreCase = true) } }
        }
    }
}