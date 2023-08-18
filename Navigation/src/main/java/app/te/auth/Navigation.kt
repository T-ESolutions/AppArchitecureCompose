package app.te.auth

import androidx.navigation.NamedNavArgument
import app.te.navigation.NavigationCommand

sealed class Navigation : NavigationCommand {
    data object Default : Navigation() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = ""
        override val popUpTo: String = ""


    }

    data object AuthGraph : Navigation() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "AUTH_GRAPH_ROUTE"
        override val popUpTo: String = ""

    }


}
