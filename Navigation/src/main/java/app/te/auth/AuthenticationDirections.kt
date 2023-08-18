package app.te.auth

import androidx.navigation.NamedNavArgument
import app.te.navigation.NavigationCommand

object AuthenticationDirections {

    val Default = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = ""
        override val popUpTo: String = ""


    }
    val AUTH_GRAPH_ROUTE = object : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = "AUTH_GRAPH_ROUTE"
        override val popUpTo: String = ""

    }

    data class SplashScreen(val popUp: String?= null) : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = SPLASH_ROUTE
        override val popUpTo: String? = popUp
    }

    data class OnBoardingScreen(val popUp: String?= null) : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = BOARDING_ROUTE

        override val popUpTo: String? = popUp

    }

    data class LoginScreenNav(val popUp: String? = null) : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = LOGIN_ROUTE
        override val popUpTo: String? = popUp
    }

    data class SignUpScreen(val popUp: String? = null) : NavigationCommand {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = SIGNUP_ROUTE
        override val popUpTo: String? = popUp
    }

}