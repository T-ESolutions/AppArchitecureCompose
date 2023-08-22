package app.te.settings

import androidx.navigation.NavType
import androidx.navigation.navArgument
import app.te.navigation.NavigationCommand
import app.te.navigation.NavigationOptions

data class TermsAndPrivacyNav(val navigationOptions: NavigationOptions = NavigationOptions()) :
    NavigationCommand {

    val PAGEARGUMENT = "page"
    val TITLEARGUMENT = "title"

    override val arguments = listOf(
        navArgument(name = PAGEARGUMENT) {
            type = NavType.StringType
        },
        navArgument(name = TITLEARGUMENT) {
            type = NavType.IntType
        },

        )

    override val destination = "terms_screen/{$PAGEARGUMENT}/{$TITLEARGUMENT}"

    override val popUpTo: String? = navigationOptions.popUpTo

    override val popUpToId: String? = navigationOptions.popUpToId

    fun passPageAndTitle(page: String, title: Int): String = "$destination/$page/$title"

}