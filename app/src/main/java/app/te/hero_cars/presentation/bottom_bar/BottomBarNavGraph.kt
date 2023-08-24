package app.te.hero_cars.presentation.bottom_bar

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.te.bottom_bar.BottomBarNav
import app.te.hero_cars.presentation.account.AccountScreen
import app.te.hero_cars.presentation.account.AccountViewModel
import app.te.hero_cars.presentation.home.ui_screens.HomeScreen
import app.te.hero_cars.presentation.home.view_model.HomeViewModel
import te.app.settings.presentation.more.MoreScreen
import te.app.settings.presentation.more.MoreViewModel
import te.app.settings.presentation.nav_graph.settingsGraph

fun NavGraphBuilder.bottomBarNavGraph(navHostController: NavHostController) {
    navigation(
        route = BottomBarNav().destination,
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
            MoreScreen(
                viewModel = viewModel,
            )
        }

        settingsGraph(navHostController)
    }
}
