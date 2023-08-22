package app.te.settings

import androidx.navigation.NamedNavArgument
import app.te.navigation.NavigationCommand
import app.te.navigation.NavigationOptions

data class ContactUsNav(val navigationOptions: NavigationOptions = NavigationOptions()) :
        NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "contact_us_screen"

        override val popUpTo: String? = navigationOptions.popUpTo

        override val popUpToId: String? = navigationOptions.popUpToId

    }