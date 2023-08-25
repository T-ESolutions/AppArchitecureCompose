package app.te.settings

import androidx.navigation.NamedNavArgument
import app.te.navigation.NavigationCommand
import app.te.navigation.NavigationOptions

data class ShareAppNav(val navigationOptions: NavigationOptions = NavigationOptions()) :
        NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "share_app"

        override val popUpTo: String? = navigationOptions.popUpTo

        override val popUpToId: String? = navigationOptions.popUpToId

    }