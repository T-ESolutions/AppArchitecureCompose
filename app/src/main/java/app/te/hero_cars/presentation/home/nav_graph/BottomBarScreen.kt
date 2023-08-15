package app.te.hero_cars.presentation.home.nav_graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import app.te.hero_cars.R

const val ROOT_GRAPH_ROUTE = "root"
const val BOTTOM_BAR_GRAPH_ROUTE = "bottom_bar"
const val HOME_ROUTE = "home_screen"
const val ACCOUNT_ROUTE = "account_screen"
const val MORE_ROUTE = "more_screen"

sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: ImageVector
) {
    data object Home : BottomBarScreen(
        route = HOME_ROUTE,
        title = R.string.home,
        icon = Icons.Default.Home
    )

    data object Account : BottomBarScreen(
        route = ACCOUNT_ROUTE,
        title = R.string.account,
        icon = Icons.Default.Person
    )

    data object More : BottomBarScreen(
        route = MORE_ROUTE,
        title = R.string.more,
        icon = Icons.Default.Settings
    )
}
