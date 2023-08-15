package app.te.hero_cars.presentation.general.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.te.hero_cars.presentation.brandsAndModels.BrandsScreen
import app.te.hero_cars.presentation.brandsAndModels.ModelsScreen
import app.te.hero_cars.presentation.brandsAndModels.view_model.BrandsAndModelsViewModel
import app.te.hero_cars.presentation.general.screens.BRAND_ID
import app.te.hero_cars.presentation.general.screens.BrandModelScreens


fun NavGraphBuilder.brandsModelsNavGraph(navHostController: NavHostController) {
    composable(
        BrandModelScreens.BrandsScreen.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
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
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
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