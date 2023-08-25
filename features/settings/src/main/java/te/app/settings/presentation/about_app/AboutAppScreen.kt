package te.app.settings.presentation.about_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import app.te.core.utils.ShowLottieLoading
import app.te.core.utils.HandleApiError
import app.te.core.extension.findActivity
import te.app.settings.presentation.settings.SettingsViewModel
import te.app.settings.R
import te.app.settings.presentation.terms_privacy.SettingsTop

@Composable
fun AboutAppScreen(
    viewModel: SettingsViewModel = viewModel()
) {
    val settingState = viewModel.settingState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.pages("about")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        SettingsTop(context, R.string.about, settingState.value.data)

        if (settingState.value.isLoading)
            ShowLottieLoading()

        if (settingState.value.failureStatus != null) {
            HandleApiError(
                activity = LocalContext.current.findActivity(),
                settingState.value.failureStatus!!
            )
        }
    }
}

@Composable
@Preview(showBackground = true, locale = "ar")
fun AboutAppPreview() {
    SettingsTop()
}