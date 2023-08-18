package te.app.auth.presentation.nav_graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.te.auth.AuthenticationDirections
import app.te.auth.AuthenticationDirections.AUTH_GRAPH_ROUTE
import app.te.auth.AuthenticationDirections.OnBoardingScreen
import app.te.auth.AuthenticationDirections.SplashScreen
import app.te.auth.AuthenticationDirections.LoginScreenNav
import app.te.auth.SPLASH_ROUTE
import app.te.core.utils.enterTransition
import app.te.core.utils.exitTransition
import app.te.core.utils.popEnterTransition
import app.te.core.utils.popExitTransition
import te.app.auth.presentation.intro.OnBoarding
import te.app.auth.presentation.intro.view_model.TutorialViewModel
import te.app.auth.presentation.login.LoginScreen
import te.app.auth.presentation.login.view_model.LogInViewModel
import te.app.auth.presentation.sign_up.SignUpScreen
import te.app.auth.presentation.sign_up.view_model.SignUpViewModel
import te.app.auth.presentation.splash.SplashScreenPage
import te.app.auth.presentation.splash.SplashViewModel

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController,
    startDestination: String = SPLASH_ROUTE
) {
    navigation(
        route = AUTH_GRAPH_ROUTE.destination,
        startDestination = startDestination
    ) {
        composable(
            SplashScreen().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashScreenPage(viewModel)
        }
        composable(
            OnBoardingScreen().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            val viewModel = hiltViewModel<TutorialViewModel>()
            OnBoarding(viewModel)
        }

        composable(
            LoginScreenNav().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            val viewModel = hiltViewModel<LogInViewModel>()
            LoginScreen(viewModel)
        }

        composable(
            AuthenticationDirections.SignUpScreen().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(navHostController, viewModel)
        }
    }
}