package app.te.hero_cars.presentation.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import app.te.account.AccountScreenNav
import app.te.hero_cars.R
import app.te.home.HomeScreenNav
import app.te.settings.MoreScreenNav


sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: ImageVector
) {
    data object Home : BottomBarScreen(
        route = HomeScreenNav().destination,
        title = R.string.home,
        icon = Icons.Default.Home
    )

    data object Account : BottomBarScreen(
        route = AccountScreenNav().destination,
        title = R.string.account,
        icon = Icons.Default.Person
    )

    data object More : BottomBarScreen(
        route = MoreScreenNav().destination,
        title = R.string.more,
        icon = Icons.Default.Settings
    )
}
