package app.te.architecture.presentation.home.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import app.te.architecture.R
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
    object Home : BottomBarScreen(
        route = HOME_ROUTE,
        title = R.string.home,
        icon = Icons.Default.Home
    )

    object Account : BottomBarScreen(
        route = ACCOUNT_ROUTE,
        title = R.string.account,
        icon = Icons.Default.Person
    )

    object More : BottomBarScreen(
        route = MORE_ROUTE,
        title = R.string.more,
        icon = Icons.Default.Settings
    )

}
