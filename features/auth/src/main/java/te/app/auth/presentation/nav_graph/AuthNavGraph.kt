package te.app.auth.presentation.nav_graph

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.te.auth.AuthenticationDirections
import app.te.auth.AuthenticationDirections.AUTH_GRAPH_ROUTE
import app.te.auth.AuthenticationDirections.OnBoardingScreen
import app.te.auth.AuthenticationDirections.SplashScreen
import app.te.auth.AuthenticationDirections.LoginScreen
import app.te.auth.BOARDING_ROUTE
import app.te.auth.LOGIN_ROUTE
import app.te.auth.SPLASH_ROUTE
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
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
        ) {
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashScreenPage(viewModel)
        }
        composable(
            OnBoardingScreen().destination,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
        ) {
            val viewModel = hiltViewModel<TutorialViewModel>()
            OnBoarding(viewModel)
        }

        composable(
            LoginScreen().destination,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
        ) {
            val viewModel = hiltViewModel<LogInViewModel>()
            LoginScreen(navHostController, viewModel)
        }

        composable(
            AuthenticationDirections.SignUpScreen().destination,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(navHostController, viewModel)
        }
    }
}