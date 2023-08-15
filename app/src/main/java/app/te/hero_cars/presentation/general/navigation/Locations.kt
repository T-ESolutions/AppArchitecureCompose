package app.te.hero_cars.presentation.general.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.te.hero_cars.presentation.auth.locations.CityScreen
import app.te.hero_cars.presentation.auth.locations.GovernmentScreen
import app.te.hero_cars.presentation.auth.locations.view_model.LocationsViewModel
import app.te.hero_cars.presentation.auth.nav_graph.GOV_ID
import app.te.hero_cars.presentation.general.screens.LocationsScreens

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.locationsNavGraph(navHostController: NavHostController) {
        composable(
            LocationsScreens.GovernmentScreen.route,
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
            val viewModel = hiltViewModel<LocationsViewModel>()
            GovernmentScreen(viewModel, navHostController)
        }
        composable(
            LocationsScreens.CitiesScreen.route,
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
                navArgument(GOV_ID) {
                    type = NavType.StringType
                }
            )
        ) {

            val viewModel = hiltViewModel<LocationsViewModel>()
            CityScreen(viewModel, navHostController)
        }

}
