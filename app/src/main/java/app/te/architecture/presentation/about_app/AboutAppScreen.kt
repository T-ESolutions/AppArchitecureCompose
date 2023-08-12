package app.te.architecture.presentation.about_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import app.te.architecture.R
import app.te.architecture.presentation.base.ShowLottieLoading
import app.te.architecture.presentation.base.extensions.CenterAlignedTopAppBarCustom
import app.te.architecture.presentation.base.extensions.HandleApiError
import app.te.architecture.presentation.base.extensions.findActivity
import app.te.architecture.presentation.base.utils.TextHtml
import app.te.architecture.presentation.settings.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(
    navHostController: NavHostController,
    viewModel: SettingsViewModel = viewModel()
) {
    val settingState = viewModel.settingState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.pages("about")
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBarCustom(
                title = R.string.about,
                navHostController = navHostController
            )
        }, content = {
            if (settingState.value.isLoading)
                ShowLottieLoading()

            if (settingState.value.failureStatus != null) {
                HandleApiError(
                    activity = LocalContext.current.findActivity(),
                    settingState.value.failureStatus!!
                )
            }

            if (settingState.value.data != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .verticalScroll(rememberScrollState())

                ) {
                    TextHtml(
                        text = settingState.value.data ?: "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                    )
                }
            }
        }
    )
}