package app.te.architecture.presentation.auth.nav_graph

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import app.te.architecture.presentation.auth.intro.OnBoarding
import app.te.architecture.presentation.auth.intro.view_model.TutorialViewModel
import app.te.architecture.presentation.auth.login.LoginScreen
import app.te.architecture.presentation.auth.login.view_model.LogInViewModel
import app.te.architecture.presentation.auth.sign_up.SignUpScreen
import app.te.architecture.presentation.auth.sign_up.view_model.SignUpViewModel
import app.te.architecture.presentation.auth.splash.SplashScreenPage
import app.te.architecture.presentation.auth.splash.SplashViewModel
import app.te.architecture.presentation.general.navigation.locationsNavGraph
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
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
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
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
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
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
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
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
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
        ) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(navHostController, viewModel)
        }
        locationsNavGraph(navHostController)

    }
}