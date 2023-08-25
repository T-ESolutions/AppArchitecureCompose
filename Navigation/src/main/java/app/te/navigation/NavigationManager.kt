package app.te.navigation

import kotlinx.coroutines.flow.MutableSharedFlow

class NavigationManager {
    var commands = MutableSharedFlow<NavigationCommand>()

    suspend fun navigate(
        directions: NavigationCommand
    ) {
        commands.emit(directions)
    }

}