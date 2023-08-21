package app.te.navigation

import androidx.navigation.NamedNavArgument

data class RootGraph(val navigationOptions: NavigationOptions = NavigationOptions()) :
    NavigationCommand {

    override val arguments = emptyList<NamedNavArgument>()

    override val destination = "GRAPH_ROUTE"
    override val popUpTo: String = ""
    override val popUpToId: String? = null

}