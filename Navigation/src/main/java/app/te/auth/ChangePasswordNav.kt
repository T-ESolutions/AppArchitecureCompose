package app.te.auth

import androidx.navigation.NamedNavArgument
import app.te.navigation.NavigationCommand
import app.te.navigation.NavigationOptions

data class ChangePasswordNav(val navigationOptions: NavigationOptions = NavigationOptions()) :
    NavigationCommand {

    override val arguments = emptyList<NamedNavArgument>()

    override val destination = "change_password"

    override val popUpTo: String? = navigationOptions.popUpTo

    override val popUpToId: String? = navigationOptions.popUpToId

}