package app.te.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {
    val arguments: List<NamedNavArgument>

    val destination: String

    val popUpTo: String?

    val popUpToId: String?



}