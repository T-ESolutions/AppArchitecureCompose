package app.te.hero_cars.presentation.contact_us

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import app.te.core.utils.ShowLottieLoading
import app.te.core.utils.CenterAlignedTopAppBarCustom
import app.te.core.utils.HandleApiError
import app.te.core.extension.findActivity
import app.te.core.extension.isNumeric
import app.te.core.utils.openBrowser
import app.te.core.utils.openDial
import app.te.hero_cars.R

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