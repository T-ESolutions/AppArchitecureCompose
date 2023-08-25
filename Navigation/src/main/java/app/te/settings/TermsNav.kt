package app.te.settings

import androidx.navigation.NavType
import androidx.navigation.navArgument
import app.te.navigation.NavigationCommand
import app.te.navigation.NavigationOptions

data class TermsNav(val navigationOptions: NavigationOptions = NavigationOptions()) :
    NavigationCommand {

    val PAGEARGUMENT = "page"
    val TITLEARGUMENT = "title"
    var route = "terms_screen/{$PAGEARGUMENT}/{$TITLEARGUMENT}"
    override val arguments = listOf(
        navArgument(name = PAGEARGUMENT) {
            type = NavType.StringType
        },
        navArgument(name = TITLEARGUMENT) {
            type = NavType.IntType
        },

        )

    override val destination = navigationOptions.destination ?: ""

    override val popUpTo: String? = navigationOptions.popUpTo

    override val popUpToId: String? = navigationOptions.popUpToId

    fun passPageAndTitle(page: String, title: Int): String = "terms_screen/$page/$title"

}