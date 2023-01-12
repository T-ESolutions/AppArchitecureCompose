package app.te.architecture.presentation.more.nav_graph

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import app.te.architecture.presentation.about_app.AboutAppScreen
import app.te.architecture.presentation.contact_us.ContactUsScreen
import app.te.architecture.presentation.more.MoreViewModel
import app.te.architecture.presentation.settings.SettingsViewModel
import app.te.architecture.presentation.terms_privacy.TermsAndPrivacyScreen
import com.google.accompanist.navigation.animation.composable


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.moreNavGraph(navHostController: NavHostController) {
    composable(
        MoreScreens.About.route,
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
        }
    ) {
        val viewModel = hiltViewModel<SettingsViewModel>()
        AboutAppScreen(navHostController, viewModel)
    }
    composable(
        MoreScreens.ContactUs.route,
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
        }
    ) {
        val viewModel = hiltViewModel<SettingsViewModel>()
        ContactUsScreen(navHostController)
    }

    composable(
        MoreScreens.TermsAndPrivacy.route,
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
        }, arguments = listOf(
            navArgument(name = PAGE_ARGUMENT) {
                type = NavType.StringType
            },
            navArgument(name = TITLE_ARGUMENT) {
                type = NavType.IntType
            },

            )
    ) {
        val viewModel = hiltViewModel<SettingsViewModel>()
        TermsAndPrivacyScreen(navHostController)
    }

}