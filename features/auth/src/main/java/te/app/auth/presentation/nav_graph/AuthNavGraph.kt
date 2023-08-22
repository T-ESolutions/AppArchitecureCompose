package te.app.auth.presentation.nav_graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.te.auth.ChangePasswordNav
import app.te.auth.LoginNav
import app.te.auth.OnBoardingNav
import app.te.auth.SignUpNav
import app.te.auth.SplashNav
import app.te.auth.UserTypeNav
import app.te.auth.VerifyNav
import app.te.core.utils.enterTransition
import app.te.core.utils.exitTransition
import app.te.core.utils.popEnterTransition
import app.te.core.utils.popExitTransition
import app.te.navigation.RootGraph
import te.app.auth.presentation.change_password.ChangePasswordScreen
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
    startDestination: String = SplashNav().destination
) {
    navigation(
        route = RootGraph().destination,
        startDestination = startDestination
    ) {
        composable(
            SplashNav().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            val viewModel = hiltViewModel<SplashViewModel>()
            SplashScreenPage(viewModel)
        }
        composable(
            OnBoardingNav().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            val viewModel = hiltViewModel<TutorialViewModel>()
            OnBoarding(viewModel)
        }
        composable(
            UserTypeNav().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            val viewModel = hiltViewModel<CheckUserTypeViewModel>()
            UserTypeScreen(viewModel)
        }

        composable(
            LoginNav().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() },
        ) {
            val viewModel = hiltViewModel<LogInViewModel>()
            LoginScreen(viewModel)
        }

        composable(
            SignUpNav().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(viewModel)
        }
        composable(
            VerifyNav().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
//            val viewModel = hiltViewModel<SignUpViewModel>()
            OtpVerificationScreen()
        }
        composable(
            ChangePasswordNav().destination,
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
            val viewModel = hiltViewModel<LogInViewModel>()
            ChangePasswordScreen(viewModel)
        }

    }
}