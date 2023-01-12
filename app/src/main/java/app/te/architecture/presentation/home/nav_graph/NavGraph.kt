package app.te.architecture.presentation.home.nav_graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import app.te.architecture.presentation.home.bottom_bar.BOTTOM_BAR_GRAPH_ROUTE
import app.te.architecture.presentation.home.bottom_bar.ROOT_GRAPH_ROUTE
import com.google.accompanist.navigation.animation.AnimatedNavHost

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
    }
}