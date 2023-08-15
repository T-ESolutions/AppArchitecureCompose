package app.te.hero_cars.presentation.home.nav_graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.te.hero_cars.presentation.add_stolen_phone.ADD_STOLEN_PHONE_ROUTE
import app.te.hero_cars.presentation.add_stolen_phone.AddStolenPhoneScreen
import app.te.hero_cars.presentation.add_stolen_phone.view_model.AddStolenPhoneViewModel
import app.te.hero_cars.presentation.general.navigation.brandsModelsNavGraph
import app.te.hero_cars.presentation.general.navigation.locationsNavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = ROOT_GRAPH_ROUTE,
        startDestination = BOTTOM_BAR_GRAPH_ROUTE,
    ) {
        bottomBarNavGraph(navHostController)
        locationsNavGraph(navHostController)
        brandsModelsNavGraph(navHostController)

        composable(ADD_STOLEN_PHONE_ROUTE,
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
            }
        ) {
            val viewModel = hiltViewModel<AddStolenPhoneViewModel>()
            AddStolenPhoneScreen(navHostController = navHostController, viewModel = viewModel)
        }
    }
}