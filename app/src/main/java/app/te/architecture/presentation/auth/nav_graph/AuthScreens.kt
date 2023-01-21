package app.te.architecture.presentation.auth.nav_graph

//Const screens routes
const val AUTH_GRAPH_ROUTE = "AUTH_GRAPH"
const val SPLASH_ROUTE = "splash_screen"
const val BOARDING_ROUTE = "boarding_screen"
const val LOGIN_ROUTE = "login_screen"
const val SIGNUP_ROUTE = "sign_up_screen"

//Const of arguments names
const val GOV_ID = "gov_id"

sealed class AuthScreens(val route: String) {
    object SplashScreen : AuthScreens(route = SPLASH_ROUTE)
    object OnBoardingScreen : AuthScreens(route = BOARDING_ROUTE)
    object LoginScreen : AuthScreens(route = LOGIN_ROUTE)
    object SignUpScreen : AuthScreens(route = SIGNUP_ROUTE)
}
