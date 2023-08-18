package app.te.hero_cars.presentation.general.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import app.te.core.utils.enterTransition
import app.te.core.utils.exitTransition
import app.te.core.utils.popEnterTransition
import app.te.core.utils.popExitTransition
import app.te.hero_cars.presentation.brandsAndModels.BrandsScreen
import app.te.hero_cars.presentation.brandsAndModels.ModelsScreen
import app.te.hero_cars.presentation.brandsAndModels.view_model.BrandsAndModelsViewModel
import app.te.hero_cars.presentation.general.screens.BRAND_ID
import app.te.hero_cars.presentation.general.screens.BrandModelScreens


fun NavGraphBuilder.brandsModelsNavGraph(navHostController: NavHostController) {
    composable(
        BrandModelScreens.BrandsScreen.route,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() },
    ) {
        val viewModel = hiltViewModel<BrandsAndModelsViewModel>()
        BrandsScreen(viewModel, navHostController)
    }
    composable(
        BrandModelScreens.ModelsScreen.route,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() },
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