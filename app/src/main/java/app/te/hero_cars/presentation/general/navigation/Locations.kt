package app.te.hero_cars.presentation.general.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.te.core.utils.enterTransition
import app.te.core.utils.exitTransition
import app.te.core.utils.popEnterTransition
import app.te.core.utils.popExitTransition
import app.te.hero_cars.presentation.general.screens.GOV_ID
import app.te.hero_cars.presentation.general.screens.LocationsScreens

fun NavGraphBuilder.locationsNavGraph(navHostController: NavHostController) {
    composable(
        LocationsScreens.GovernmentScreen.route,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
    ) {
//            val viewModel = hiltViewModel<LocationsViewModel>()
//            GovernmentScreen(viewModel, navHostController)
    }
    composable(
        LocationsScreens.CitiesScreen.route,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() },
        arguments = listOf(
            navArgument(GOV_ID) {
                type = NavType.StringType
            }
        )
    ) {

//            val viewModel = hiltViewModel<LocationsViewModel>()
//            CityScreen(viewModel, navHostController)
    }

}
