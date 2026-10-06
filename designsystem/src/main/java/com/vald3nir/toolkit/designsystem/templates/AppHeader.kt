package com.vald3nir.toolkit.designsystem.templates

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle

@Composable
fun ToolkitAppHeader(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    description: String,
    @DrawableRes appLogo: Int
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f) // Ocupa o espaço disponível, sem empurrar a imagem
                .padding(end = ToolkitSpacingMd) // Espaçamento de segurança entre o texto e a imagem
        ) {
            ToolkitText(text = title, style = ToolkitTextStyle.TitleLarge)
            subtitle?.let {
                ToolkitText(text = it, style = ToolkitTextStyle.BodyMedium)
            }
            ToolkitSpaceHeight(ToolkitSpacingSm)
            ToolkitText(text = description, style = ToolkitTextStyle.BodyLarge)
        }
        Image(
            painter = painterResource(id = appLogo),
            contentDescription = "App logo",
            contentScale = ContentScale.Fit, // Mantém a proporção original da imagem
            modifier = Modifier.size(80.dp)  // Define largura e altura fixas para manter o tamanho estável
        )
    }
}