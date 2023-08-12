package app.te.architecture.presentation.auth.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import app.te.architecture.R
import app.te.architecture.presentation.auth.locations.view_model.LocationsViewModel
import app.te.architecture.presentation.auth.nav_graph.GOV_ID
import app.te.architecture.data.general.data_source.dto.countries.CityModel
import app.te.architecture.presentation.base.extensions.CenterAlignedTopAppBarCustom
import app.te.architecture.presentation.base.extensions.navigateUpWithResult
import app.te.architecture.presentation.general.screens.CITIES_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityScreen(
    viewModel: LocationsViewModel = viewModel(),
    navHostController: NavHostController,
) {
    val cityData: LazyPagingItems<CityModel> =
        viewModel.citiesState.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBarCustom(
                navHostController = navHostController,
                title = R.string.city
            )
        }, content = {
            LaunchedEffect(key1 = true) {
                viewModel.savedStateHandle.get<String>(GOV_ID)?.let { gov_id ->
                    viewModel.getCitiesByGovern(gov_id)
                }
            }
            when (cityData.loadState.refresh) {
                LoadState.Loading -> {
                    ShimmerLoadingContent()
                }
                is LoadState.Error -> {
//                   AlerterError()
                }
                else -> {
                    CityContent(it, cityData, navHostController)
                }
            }

        })
}

@Composable
fun CityContent(
    paddingValues: PaddingValues,
    cityItems: LazyPagingItems<CityModel>,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
//            itemsIndexed(cityItems) { _, item ->
//                Column(modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
//                        navController.navigateUpWithResult(key = CITIES_ROUTE, data = item)
//                    }) {
//                    Text(
//                        text = item?.name ?: "Cairo",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(10.dp),
//                        style = MaterialTheme.typography.labelMedium
//                    )
//                    Divider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)
//                }
//            }
        }
    }
}
