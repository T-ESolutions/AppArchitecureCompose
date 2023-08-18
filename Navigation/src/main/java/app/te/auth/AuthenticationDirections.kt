package app.te.auth

import androidx.navigation.NamedNavArgument
import app.te.navigation.NavigationCommand

sealed class AuthenticationDirections : Navigation() {

    data class SplashScreen(val popUp: String? = null) : AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = SPLASH_ROUTE
        override val popUpTo: String? = popUp
    }

    data class OnBoardingScreen(val popUp: String? = null) : AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = BOARDING_ROUTE

        override val popUpTo: String? = popUp

    }

    data class UserTypeScreen(val popUp: String? = null) : AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = USER_TYPE_ROUTE

        override val popUpTo: String? = popUp

    }

    data class LoginScreenNav(val popUp: String? = null) : AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = LOGIN_ROUTE
        override val popUpTo: String? = popUp
    }

    data class SignUpScreen(val popUp: String? = null) : AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = SIGNUP_ROUTE
        override val popUpTo: String? = popUp
    }

}