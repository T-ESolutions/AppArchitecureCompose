package app.te.architecture.presentation.general.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import app.te.architecture.presentation.auth.locations.CityScreen
import app.te.architecture.presentation.auth.locations.GovernmentScreen
import app.te.architecture.presentation.auth.locations.view_model.LocationsViewModel
import app.te.architecture.presentation.auth.nav_graph.GOV_ID
import app.te.architecture.presentation.general.screens.LocationsScreens
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.locationsNavGraph(navHostController: NavHostController) {
        composable(
            LocationsScreens.GovernmentScreen.route,
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
            val viewModel = hiltViewModel<LocationsViewModel>()
            GovernmentScreen(viewModel, navHostController)
        }
        composable(
            LocationsScreens.CitiesScreen.route,
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
                navArgument(GOV_ID) {
                    type = NavType.StringType
                }
            )
        ) {

            val viewModel = hiltViewModel<LocationsViewModel>()
            CityScreen(viewModel, navHostController)
        }

}
