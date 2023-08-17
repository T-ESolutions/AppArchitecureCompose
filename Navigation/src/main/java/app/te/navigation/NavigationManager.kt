package app.te.navigation

import app.te.auth.AuthenticationDirections.Default
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {
    var commands = MutableStateFlow(Default)

    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }

}