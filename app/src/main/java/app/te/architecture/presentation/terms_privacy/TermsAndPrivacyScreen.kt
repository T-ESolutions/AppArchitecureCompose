package app.te.architecture.presentation.terms_privacy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import app.te.architecture.presentation.base.ShowLottieLoading
import app.te.architecture.presentation.base.extensions.HandleApiError
import app.te.architecture.presentation.base.extensions.findActivity
import app.te.architecture.presentation.base.utils.TextHtml
import app.te.architecture.presentation.more.nav_graph.PAGE_ARGUMENT
import app.te.architecture.presentation.settings.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndPrivacyScreen(
    navHostController: NavHostController,
    viewModel: SettingsViewModel = viewModel()
) {
    val settingState = viewModel.settingState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.savedStateHandle.get<String>(PAGE_ARGUMENT)?.let { page ->
            viewModel.pages(page)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = viewModel.title),
                        color = MaterialTheme.colorScheme.background,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                }
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