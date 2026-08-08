package com.vald3nir.toolkit.auth.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vald3nir.toolkit.auth.R
import com.vald3nir.toolkit.core.baseclasses.BaseUiState
import com.vald3nir.toolkit.core.utils.extensions.openLinkURL
import com.vald3nir.toolkit.core.utils.extensions.openWifiSettings
import com.vald3nir.toolkit.designsystem.annotations.ThemePreviews
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceHeight
import com.vald3nir.toolkit.designsystem.components.ToolkitSpaceWidth
import com.vald3nir.toolkit.designsystem.components.ToolkitSpacingMd
import com.vald3nir.toolkit.designsystem.components.containers.ToolkitLoadingWheel
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIcon
import com.vald3nir.toolkit.designsystem.components.icons.ToolkitIconCatalog
import com.vald3nir.toolkit.designsystem.components.notifications.ToolkitDisclaimer
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitText
import com.vald3nir.toolkit.designsystem.components.texts.ToolkitTextStyle
import com.vald3nir.toolkit.designsystem.extensions.ToolkitPreviewContainer
import com.vald3nir.toolkit.designsystem.templates.ToolkitScaffold

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    appPrivacyPolicyURL: String,
    appTermsUseLink: String,
    webGoogleClientID: String,
    onSuccess: () -> Unit = {},
) {
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    val hasInternetConnection by viewModel.hasInternetConnection.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState is BaseUiState.FinishState) {
        LaunchedEffect(Unit) {
            onSuccess()
        }
        return
    }

    ScreenContent(
        showLoading = (uiState as? BaseUiState.LoadingState)?.show == true,
        hasInternetConnection = hasInternetConnection,
        snackBarHostState = snackBarHostState,
        onClickLogin = {
            viewModel.signInWithGoogle(context, webGoogleClientID)
        },
        onClickTerms = {
            context.openLinkURL(url = appTermsUseLink)
        },
        onClickPrivacyPolicy = {
            context.openLinkURL(url = appPrivacyPolicyURL)
        },
    )
}

@Composable
private fun ScreenContent(
    hasInternetConnection: Boolean = true,
    showLoading: Boolean = false,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onClickLogin: () -> Unit = {},
    onClickTerms: () -> Unit = {},
    onClickPrivacyPolicy: () -> Unit = {},
) {
    ToolkitScaffold(snackBarHostState = snackBarHostState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(ToolkitSpacingMd)
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
            Header()
            Body(
                modifier = Modifier.align(Alignment.Center),
                hasInternetConnection = hasInternetConnection,
                showLoading = showLoading,
                onClickLogin = onClickLogin
            )
            TermsAndPrivacyText(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClickTerms = onClickTerms,
                onClickPrivacyPolicy = onClickPrivacyPolicy
            )
        }
    }
}

@Composable
private fun BoxScope.Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToolkitSpaceHeight()
        ToolkitIcon(
            imageVector = ToolkitIconCatalog.AccountCircle,
            modifier = Modifier.size(48.dp)
        )
        ToolkitSpaceHeight()
        ToolkitText(
            text = stringResource(R.string.auth_screen_title),
            style = ToolkitTextStyle.TitleMedium
        )
    }
}

@Composable
private fun Body(
    modifier: Modifier = Modifier,
    showLoading: Boolean = false,
    hasInternetConnection: Boolean = true,
    onClickLogin: () -> Unit = {}
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacingMd)
    ) {

        item { LoginInfo() }

        item {
            LoginButton(
                enabled = hasInternetConnection,
                showLoading = showLoading,
                onClickLogin = onClickLogin
            )
        }

        if (!hasInternetConnection) {
            item {
                ToolkitDisclaimer(
                    description = stringResource(R.string.auth_create_account_offline_warning),
                    imageVector = ToolkitIconCatalog.WifiOff,
                    linkText = stringResource(R.string.auth_wifi_connect),
                    onClickLink = { context.openWifiSettings() }
                )
            }
        }
    }
}

@Composable
private fun LoginInfo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        ToolkitIcon(
            imageVector = ToolkitIconCatalog.CloudSync,
            modifier = Modifier.size(32.dp)
        )
        ToolkitSpaceWidth()
        ToolkitText(
            text = stringResource(R.string.auth_login_info),
            style = ToolkitTextStyle.LabelMedium,
        )
    }
}

@Composable
private fun LoginButton(
    enabled: Boolean = true,
    showLoading: Boolean = false,
    onClickLogin: () -> Unit = {}
) {
    Button(
        enabled = enabled && !showLoading,
        onClick = onClickLogin,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo_google),
                tint = Color.Unspecified,
                contentDescription = stringResource(R.string.auth_btn_login_label),
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (showLoading) {
                ToolkitLoadingWheel()
            } else {
                ToolkitText(
                    text = stringResource(R.string.auth_sign_in_with_your_google_account),
                    style = ToolkitTextStyle.LabelMedium,
                    textColor = Color.Black
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun TermsAndPrivacyText(
    modifier: Modifier = Modifier,
    onClickTerms: () -> Unit = {},
    onClickPrivacyPolicy: () -> Unit = {},
) {
    val linkStyle = TextLinkStyles(
        style = SpanStyle(
            color = MaterialTheme.colorScheme.primary,
            textDecoration = TextDecoration.Underline
        )
    )

    fun clickableLink(tag: String, onClick: () -> Unit) = LinkAnnotation.Clickable(
        tag = tag,
        styles = linkStyle,
        linkInteractionListener = { onClick() }
    )

    val annotatedText = buildAnnotatedString {
        append(stringResource(R.string.auth_terms_politics_description))
        append(" ")
        withLink(clickableLink(tag = "TERMS", onClick = onClickTerms)) {
            append(stringResource(R.string.auth_terms_and_conditions))
        }
        append(" e ")
        withLink(clickableLink(tag = "PRIVACY", onClick = onClickPrivacyPolicy)) {
            append(stringResource(R.string.auth_privacy_policy))
        }
    }
    Text(
        text = annotatedText,
        style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@ThemePreviews
@Composable
private fun Preview() {
    ToolkitPreviewContainer {
        ScreenContent()
    }
}