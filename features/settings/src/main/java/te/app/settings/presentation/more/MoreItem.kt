package te.app.settings.presentation.more

import app.te.navigation.NavigationCommand


data class MoreItem(
    val title: Int,
    val icon: Int,
    val route: NavigationCommand
)
