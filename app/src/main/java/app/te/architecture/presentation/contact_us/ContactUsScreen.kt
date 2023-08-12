package app.te.architecture.presentation.contact_us

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import app.te.architecture.R
import app.te.architecture.presentation.base.ShowLottieLoading
import app.te.architecture.presentation.base.extensions.CenterAlignedTopAppBarCustom
import app.te.architecture.presentation.base.extensions.HandleApiError
import app.te.architecture.presentation.base.extensions.findActivity
import app.te.architecture.presentation.base.extensions.isNumeric
import app.te.architecture.presentation.base.utils.openBrowser
import app.te.architecture.presentation.base.utils.openDial

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsScreen(
    navHostController: NavHostController,
    viewModel: ContactUsViewModel = viewModel()
) {
    val contactState = viewModel.contactState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.getContactUs()
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBarCustom(
                navHostController = navHostController,
                title = R.string.contact_us
            )
        }, content = { paddingValues ->

            if (contactState.value.isLoading)
                ShowLottieLoading()

            if (contactState.value.failureStatus != null) {
                HandleApiError(
                    activity = LocalContext.current.findActivity(),
                    contactState.value.failureStatus!!
                )
            }
            if (contactState.value.data != null) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(paddingValues),
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                ) {
                    items(contactState.value.data!!) { item ->
                        ContactItem(item = item) {
                            if (item.link.isNumeric(item.link))
                                openDial(context, item.link)
                            else
                                openBrowser(context, item.link)
                        }
                    }
                }
            }
        })
}