package app.te.architecture.presentation.home.nav_graph

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.te.architecture.presentation.add_stolen_phone.ADD_STOLEN_PHONE_ROUTE
import app.te.architecture.presentation.add_stolen_phone.AddStolenPhoneScreen
import app.te.architecture.presentation.add_stolen_phone.view_model.AddStolenPhoneViewModel
import app.te.architecture.presentation.general.navigation.brandsModelsNavGraph
import app.te.architecture.presentation.general.navigation.locationsNavGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    AnimatedNavHost(
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
            }
        ) {
            val viewModel = hiltViewModel<AddStolenPhoneViewModel>()
            AddStolenPhoneScreen(navHostController = navHostController, viewModel = viewModel)
        }
    }
}