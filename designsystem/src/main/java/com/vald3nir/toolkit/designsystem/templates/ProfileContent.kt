package com.vald3nir.toolkit.designsystem.templates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vald3nir.toolkit.designsystem.R
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingSm
import com.vald3nir.toolkit.designsystem.components.buttons.ToolkitBaseButton
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconAvatar
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer

@Composable
fun ToolkitProfileContent(
    userName: String? = null,
    userEmail: String? = null,
    isAuthenticated: Boolean = false,
    userImageUrl: String? = null,
    onClickLogin: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = ToolkitSpacingMd),
        horizontalAlignment = CenterHorizontally
    ) {
        ToolkitIconAvatar(
            iconSize = 52.dp,
            userImageUrl = userImageUrl
        )
        ToolkitSpaceHeight(ToolkitSpacingSm)

        if (isAuthenticated) {
            ToolkitText(
                text = userName.orEmpty(),
                style = ToolkitTextStyle.TitleLarge
            )
            ToolkitText(
                text = userEmail.orEmpty(),
                style = ToolkitTextStyle.TitleSmall
            )
        } else {
            ToolkitText(
                text = stringResource(R.string.profile_screen_user_not_authenticated),
                style = ToolkitTextStyle.TitleLarge
            )
            ToolkitSpaceHeight()
            ToolkitBaseButton(onClick = onClickLogin, text = stringResource(R.string.profile_btn_login))
        }
        ToolkitSpaceHeight()
        HorizontalDivider()
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer(modifier = Modifier.size(400.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ) {
            ToolkitProfileContent(
                userName = "Fulano de Tal",
                userEmail = "fulano@gmail.com",
                isAuthenticated = true
            )
            ToolkitProfileContent(
                userName = "Fulano de Tal",
                userEmail = "fulano@gmail.com",
                isAuthenticated = false
            )
        }
    }
}