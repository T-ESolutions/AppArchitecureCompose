package app.te.auth

import androidx.navigation.NamedNavArgument
import app.te.navigation.NavigationCommand
import app.te.navigation.NavigationOptions

sealed class Navigation : NavigationCommand {
    data object Default : Navigation() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = ""
        override val popUpTo: String = ""
        override val popUpToId: String? = null


    }

    data class AuthGraph(val navigationOptions: NavigationOptions = NavigationOptions()) :
        Navigation() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "AUTH_GRAPH_ROUTE"
        override val popUpTo: String = ""
        override val popUpToId: String? = null

    }


}
