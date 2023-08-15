package te.app.auth.presentation.nav_graph

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
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
    startDestination: String
) {
    navigation(
        route = AUTH_GRAPH_ROUTE,
        startDestination = startDestination
    ) {
        composable(
            AuthScreens.SplashScreen.route,
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
            SplashScreenPage(navHostController, viewModel)
        }
        composable(
            AuthScreens.OnBoardingScreen.route,
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
            OnBoarding(navHostController, viewModel)
        }

        composable(
            AuthScreens.LoginScreen.route,
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
            AuthScreens.SignUpScreen.route,
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
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(navHostController, viewModel)
        }
    }
}