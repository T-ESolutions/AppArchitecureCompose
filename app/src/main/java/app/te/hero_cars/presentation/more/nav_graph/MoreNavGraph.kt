package app.te.hero_cars.presentation.more.nav_graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.te.hero_cars.presentation.about_app.AboutAppScreen
import app.te.hero_cars.presentation.contact_us.ContactUsScreen
import app.te.hero_cars.presentation.contact_us.ContactUsViewModel
import app.te.hero_cars.presentation.settings.SettingsViewModel
import app.te.hero_cars.presentation.terms_privacy.TermsAndPrivacyScreen


fun NavGraphBuilder.moreNavGraph(navHostController: NavHostController) {
    composable(
        MoreScreens.About.route,
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
        val viewModel = hiltViewModel<SettingsViewModel>()
        AboutAppScreen(navHostController, viewModel)
    }
    composable(
        MoreScreens.ContactUs.route,
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
        val viewModel = hiltViewModel<ContactUsViewModel>()
        ContactUsScreen(navHostController,viewModel)
    }

    composable(
        MoreScreens.TermsAndPrivacy.route,
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