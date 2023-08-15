package app.te.hero_cars.presentation.home.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.te.hero_cars.presentation.account.AccountScreen
import app.te.hero_cars.presentation.account.AccountViewModel
import app.te.hero_cars.presentation.home.ui_screens.HomeScreen
import app.te.hero_cars.presentation.home.view_model.HomeViewModel
import app.te.hero_cars.presentation.more.MoreScreen
import app.te.hero_cars.presentation.more.MoreViewModel
import app.te.hero_cars.presentation.more.nav_graph.moreNavGraph

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.bottomBarNavGraph(navHostController: NavHostController) {
    navigation(
        route = BOTTOM_BAR_GRAPH_ROUTE,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navHostController = navHostController, viewModel = viewModel)
        }
        composable(route = BottomBarScreen.Account.route) {
            val viewModel = hiltViewModel<AccountViewModel>()
            AccountScreen(navHostController = navHostController, viewModel)
        }
        composable(route = BottomBarScreen.More.route) {
            val viewModel = hiltViewModel<MoreViewModel>()
            MoreScreen(viewModel = viewModel, navHostController = navHostController)
        }

        moreNavGraph(navHostController)
    }
}
