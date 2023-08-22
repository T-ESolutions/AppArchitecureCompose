package app.te.auth

import androidx.navigation.NamedNavArgument
import app.te.navigation.NavigationCommand
import app.te.navigation.NavigationOptions

data class UserTypeNav(val navigationOptions: NavigationOptions = NavigationOptions()) :
        NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "user_type_screen"

        override val popUpTo: String? = navigationOptions.popUpTo

        override val popUpToId: String? = navigationOptions.popUpToId

    }