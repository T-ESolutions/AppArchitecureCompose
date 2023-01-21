package app.te.architecture.presentation.brandsAndModels

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import app.te.architecture.R
import app.te.architecture.data.home.dto.Brand
import app.te.architecture.presentation.base.extensions.CenterAlignedTopAppBarCustom
import app.te.architecture.presentation.base.extensions.LoadAsyncImage
import app.te.architecture.presentation.base.extensions.navigateUpWithResult
import app.te.architecture.presentation.base.utils.animatedShimmer
import app.te.architecture.presentation.brandsAndModels.view_model.BrandsAndModelsViewModel
import app.te.architecture.presentation.general.screens.BRANDS_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandsScreen(
    viewModel: BrandsAndModelsViewModel = viewModel(),
    navHostController: NavHostController,
) {
    val brandsData: LazyPagingItems<Brand> =
        viewModel.brandsResponse.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBarCustom(
                navHostController = navHostController,
                title = R.string.brand
            )
        }, content = {
            LaunchedEffect(key1 = true) {
                viewModel.getBrands()
            }
            when (brandsData.loadState.refresh) {
                LoadState.Loading -> {
                    ShimmerBrandsLoadingContent()
                }
                is LoadState.Error -> {
//                   AlerterError()
                }
                else -> {
                    BrandContent(it, brandsData, navHostController)
                }
            }

        })
}

@Composable
fun BrandContent(
    paddingValues: PaddingValues,
    brandItems: LazyPagingItems<Brand>,
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
            itemsIndexed(brandItems) { _, item ->
                ConstraintLayout(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigateUpWithResult(key = BRANDS_ROUTE, data = item)
                    }) {
                    val (iv_brand, t_brand_name, divider) = createRefs()
                    LoadAsyncImage(
                        modifier = Modifier
                            .constrainAs(iv_brand) {
                                start.linkTo(parent.start, 4.dp)
                                top.linkTo(parent.top, 4.dp)
                            }
                            .width(70.dp)
                            .height(70.dp)
                            .clip(shape = CircleShape),
                        contentScale = ContentScale.Crop,
                        url = item?.image ?: ""
                    )
                    Text(
                        text = item?.title ?: "test",
                        modifier = Modifier
                            .constrainAs(t_brand_name) {
                                start.linkTo(iv_brand.end, 4.dp)
                                top.linkTo(iv_brand.top)
                                bottom.linkTo(iv_brand.bottom)
                                end.linkTo(parent.end, 4.dp)
                                width = Dimension.fillToConstraints
                            },
                        style = MaterialTheme.typography.labelMedium
                    )
                    Divider(
                        color = MaterialTheme.colorScheme.outline,
                        thickness = 1.dp,
                        modifier = Modifier.constrainAs(divider) {
                            top.linkTo(iv_brand.bottom, 10.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ShimmerBrandsLoadingContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        repeat(15) {
            BrandShimmerLoaderItem()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BrandShimmerLoaderItem() {
    val animatedShimmer = animatedShimmer()

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
