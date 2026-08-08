package com.vald3nir.toolkit.designsystem.theme.domain

import com.vald3nir.toolkit.designsystem.theme.brands.BaseThemeBrand
import com.vald3nir.toolkit.designsystem.theme.brands.BlueThemeBrand
import com.vald3nir.toolkit.designsystem.theme.brands.GreenThemeBrand
import com.vald3nir.toolkit.designsystem.theme.brands.PurpleThemaBrand
import com.vald3nir.toolkit.designsystem.theme.brands.RedThemeBrand
import com.vald3nir.toolkit.designsystem.theme.brands.YellowThemeBrand

enum class ThemeBrandEnum(private val label: String) {
    BLUE("Azul"),
    GREEN("Verde"),
    PURPLE("Roxo"),
    RED("Vermelho"),
    YELLOW("Amarelo");

    val key: String
        get() = label

    fun toThemeBrand(): BaseThemeBrand = when (this) {
        BLUE -> BlueThemeBrand
        GREEN -> GreenThemeBrand
        PURPLE -> PurpleThemaBrand
        RED -> RedThemeBrand
        YELLOW -> YellowThemeBrand
    }

    companion object {

        fun fromKey(key: String?): ThemeBrandEnum? {
            return key
                ?.let { value -> entries.firstOrNull { it.label.equals(value, ignoreCase = true) } }
        }

    }
}