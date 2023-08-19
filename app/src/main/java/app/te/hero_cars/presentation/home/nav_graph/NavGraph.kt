package app.te.hero_cars.presentation.home.nav_graph

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
import app.te.auth.Navigation
import app.te.core.utils.enterTransition
import app.te.core.utils.exitTransition
import app.te.core.utils.popEnterTransition
import app.te.core.utils.popExitTransition
import app.te.hero_cars.presentation.bottom_bar.ROOT_GRAPH_ROUTE
import app.te.hero_cars.presentation.bottom_bar.bottomBarNavGraph
import te.app.auth.presentation.nav_graph.authNavGraph

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = ROOT_GRAPH_ROUTE,
        startDestination = Navigation.AuthGraph().destination,
    ) {
        authNavGraph()
        bottomBarNavGraph(navHostController)
        locationsNavGraph(navHostController)
        brandsModelsNavGraph(navHostController)

        composable(ADD_STOLEN_PHONE_ROUTE,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
            val viewModel = hiltViewModel<AddStolenPhoneViewModel>()
            AddStolenPhoneScreen(navHostController = navHostController, viewModel = viewModel)
        }
    }
}