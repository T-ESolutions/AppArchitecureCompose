package app.te.navigation

import app.te.auth.Navigation
import kotlinx.coroutines.flow.MutableSharedFlow

class NavigationManager {
    var commands = MutableSharedFlow<Navigation>()
    suspend fun navigate(
        directions: Navigation
    ) {
        commands.emit(directions)
    }

}