package app.te.hero_cars.presentation.brandsAndModels

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import app.te.hero_cars.R
import app.te.hero_cars.data.home.dto.Model
import app.te.core.utils.CenterAlignedTopAppBarCustom
import app.te.core.utils.animatedShimmer
import app.te.hero_cars.presentation.brandsAndModels.view_model.BrandsAndModelsViewModel
import app.te.hero_cars.presentation.general.screens.BRAND_ID

@Composable
fun ModelsScreen(
    viewModel: BrandsAndModelsViewModel = viewModel(),
    navHostController: NavHostController,
) {
    val modelsData: LazyPagingItems<Model> =
        viewModel.modelsResponse.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBarCustom(
                navHostController = navHostController,
                title = R.string.model
            )
        }, content = {
            LaunchedEffect(key1 = true) {
                viewModel.savedStateHandle.get<String>(BRAND_ID)?.let { brand_id ->
                    viewModel.getModels(brand_id)
                }
            }
            when (modelsData.loadState.refresh) {
                LoadState.Loading -> {
                    ShimmerModelLoadingContent()
                }
                is LoadState.Error -> {
//                   AlerterError()
                }
                else -> {
                    ModelsContent(it, modelsData, navHostController)
                }
            }

        })
}

@Composable
fun ModelsContent(
    paddingValues: PaddingValues,
    brandItems: LazyPagingItems<Model>,
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
//            itemsIndexed(brandItems) { _, item ->
//                ConstraintLayout(modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
//                        navController.navigateUpWithResult(key = MODELS_ROUTE, data = item)
//                    }) {
//                    val (iv_model, t_model_name, divider) = createRefs()
//                    LoadAsyncImage(
//                        modifier = Modifier
//                            .constrainAs(iv_model) {
//                                start.linkTo(parent.start, 4.dp)
//                                top.linkTo(parent.top, 4.dp)
//                            }
//                            .width(70.dp)
//                            .height(70.dp)
//                            .clip(shape = CircleShape),
//                        contentScale = ContentScale.Crop,
//                        url = item?.image ?: ""
//                    )
//                    Text(
//                        text = item?.title ?: "test",
//                        modifier = Modifier
//                            .constrainAs(t_model_name) {
//                                start.linkTo(iv_model.end, 4.dp)
//                                top.linkTo(iv_model.top)
//                                bottom.linkTo(iv_model.bottom)
//                                end.linkTo(parent.end, 4.dp)
//                                width = Dimension.fillToConstraints
//                            },
//                        style = MaterialTheme.typography.labelMedium
//                    )
//                    Divider(
//                        color = MaterialTheme.colorScheme.outline,
//                        thickness = 1.dp,
//                        modifier = Modifier.constrainAs(divider) {
//                            top.linkTo(iv_model.bottom, 10.dp)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                        }
//                    )
//                }
//            }
        }
    }
}

@Composable
fun ShimmerModelLoadingContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        repeat(15) {
            ModelShimmerLoaderItem()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ModelShimmerLoaderItem() {
    val animatedShimmer = animatedShimmer()

    Spacer(modifier = Modifier.height(10.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .height(70.dp)
                .width(70.dp)
                .padding(10.dp)
                .clip(CircleShape)
                .background(animatedShimmer)
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .background(animatedShimmer)

        )
    }
}
