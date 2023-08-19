package app.te.auth

import androidx.navigation.NamedNavArgument
import app.te.navigation.NavigationCommand
import app.te.navigation.NavigationOptions

sealed class AuthenticationDirections : Navigation() {

    data class SplashScreen(val navigationOptions: NavigationOptions = NavigationOptions()) :
        AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = SPLASH_ROUTE
        override val popUpTo: String? = navigationOptions.popUpTo
        override val popUpToId: String? = navigationOptions.popUpToId

    }

    data class OnBoardingScreen(val navigationOptions: NavigationOptions = NavigationOptions()) :
        AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = BOARDING_ROUTE

        override val popUpTo: String? = navigationOptions.popUpTo

        override val popUpToId: String? = navigationOptions.popUpToId

    }

    data class UserTypeScreen(val navigationOptions: NavigationOptions = NavigationOptions()) :
        AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = USER_TYPE_ROUTE

        override val popUpTo: String? = navigationOptions.popUpTo

        override val popUpToId: String? = navigationOptions.popUpToId

    }

    data class LoginScreenNav(val navigationOptions: NavigationOptions = NavigationOptions()) :
        AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = LOGIN_ROUTE

        override val popUpTo: String? = navigationOptions.popUpTo

        override val popUpToId: String? = navigationOptions.popUpToId

    }

    data class SignUpScreen(val navigationOptions: NavigationOptions = NavigationOptions()) :
        AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = SIGNUP_ROUTE

        override val popUpTo: String? = navigationOptions.popUpTo

        override val popUpToId: String? = navigationOptions.popUpToId

    }

    data class VerifyScreen(val navigationOptions: NavigationOptions = NavigationOptions()) :
        AuthenticationDirections() {

        override val arguments = emptyList<NamedNavArgument>()

        override val destination = VERIFY_ROUTE

        override val popUpTo: String? = navigationOptions.popUpTo

        override val popUpToId: String? = navigationOptions.popUpToId

    }

}