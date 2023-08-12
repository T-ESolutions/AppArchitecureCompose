package app.te.architecture.presentation.home.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import app.te.architecture.presentation.account.AccountScreen
import app.te.architecture.presentation.account.AccountViewModel
import app.te.architecture.presentation.home.ui_screens.HomeScreen
import app.te.architecture.presentation.home.view_model.HomeViewModel
import app.te.architecture.presentation.more.MoreScreen
import app.te.architecture.presentation.more.MoreViewModel
import app.te.architecture.presentation.more.nav_graph.moreNavGraph
import com.google.accompanist.navigation.animation.composable

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
