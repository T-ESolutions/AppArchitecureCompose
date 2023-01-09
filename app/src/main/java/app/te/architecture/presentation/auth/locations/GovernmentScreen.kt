package app.te.architecture.presentation.auth.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import app.te.architecture.R
import app.te.architecture.presentation.auth.locations.view_model.LocationsViewModel
import app.te.architecture.presentation.auth.nav_graph.GOVERNMENT_ROUTE
import app.te.architecture.data.general.data_source.dto.countries.Government
import app.te.architecture.presentation.base.extensions.navigateUpWithResult
import app.te.architecture.presentation.base.utils.animatedShimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GovernmentScreen(
    viewModel: LocationsViewModel = viewModel(),
    navHostController: NavHostController,
) {
    val governData: LazyPagingItems<Government> =
        viewModel.governmentState.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.govern),
                        color = MaterialTheme.colorScheme.background,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }, content = {
            LaunchedEffect(key1 = true) {
                viewModel.getGovernments()
            }
            when (governData.loadState.refresh) {
                LoadState.Loading -> {
                    ShimmerLoadingContent()
                }
                is LoadState.Error -> {
//                   AlerterError()
                }
                else -> {
                    GovernContent(it, governData, navHostController)
                }
            }

        })
}

@Composable
fun GovernContent(
    paddingValues: PaddingValues,
    governItems: LazyPagingItems<Government>,
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
            itemsIndexed(governItems) { _, item ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigateUpWithResult(key = GOVERNMENT_ROUTE, data = item)
                    }) {
                    Text(
                        text = item?.title ?: "test",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Divider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)
                }
            }
        }
    }
}

@Composable
fun ShimmerLoadingContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        repeat(15) {
            GovernShimmerLoaderItem()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GovernShimmerLoaderItem() {
    val animatedShimmer = animatedShimmer()

    Spacer(modifier = Modifier.height(10.dp))

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(
            modifier = Modifier
                .height(50.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .background(animatedShimmer)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .background(animatedShimmer)
        )
    }
}
