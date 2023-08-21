package te.app.auth.presentation.nav_graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.te.auth.LoginScreenNav
import app.te.auth.OnBoardingScreen
import app.te.auth.SPLASH_ROUTE
import app.te.auth.SignUpScreenNav
import app.te.auth.SplashScreen
import app.te.auth.UserTypeScreenNav
import app.te.auth.VerifyScreen
import app.te.core.utils.enterTransition
import app.te.core.utils.exitTransition
import app.te.core.utils.popEnterTransition
import app.te.core.utils.popExitTransition
import app.te.navigation.RootGraph
import te.app.auth.presentation.intro.OnBoarding
import te.app.auth.presentation.intro.view_model.TutorialViewModel
import te.app.auth.presentation.login.LoginScreen
import te.app.auth.presentation.login.view_model.LogInViewModel
import te.app.auth.presentation.otp.OtpVerificationScreen
import te.app.auth.presentation.sign_up.SignUpScreen
import te.app.auth.presentation.sign_up.view_model.SignUpViewModel
import te.app.auth.presentation.splash.SplashScreenPage
import te.app.auth.presentation.splash.SplashViewModel
import te.app.auth.presentation.user_type.CheckUserTypeViewModel
import te.app.auth.presentation.user_type.UserTypeScreen

fun NavGraphBuilder.authNavGraph(
    startDestination: String = SPLASH_ROUTE
) {
    navigation(
        route = RootGraph().destination,
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
            UserTypeScreenNav().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            val viewModel = hiltViewModel<CheckUserTypeViewModel>()
            UserTypeScreen(viewModel)
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
            SignUpScreenNav().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(viewModel)
        }
        composable(
            VerifyScreen().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
//            val viewModel = hiltViewModel<SignUpViewModel>()
            OtpVerificationScreen()
        }

    }
}