package app.te.architecture.presentation.general.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import app.te.architecture.presentation.auth.nav_graph.GOV_ID
import app.te.architecture.presentation.brandsAndModels.BrandsScreen
import app.te.architecture.presentation.brandsAndModels.ModelsScreen
import app.te.architecture.presentation.brandsAndModels.view_model.BrandsAndModelsViewModel
import app.te.architecture.presentation.general.screens.BRAND_ID
import app.te.architecture.presentation.general.screens.BrandModelScreens
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.brandsModelsNavGraph(navHostController: NavHostController) {
    composable(
        BrandModelScreens.BrandsScreen.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
    ) {
        val viewModel = hiltViewModel<BrandsAndModelsViewModel>()
        BrandsScreen(viewModel, navHostController)
    }
    composable(
        BrandModelScreens.ModelsScreen.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        arguments = listOf(
            navArgument(BRAND_ID) {
                type = NavType.StringType
            }
        )
    ) {

        val viewModel = hiltViewModel<BrandsAndModelsViewModel>()
        ModelsScreen(viewModel, navHostController)
    }

}