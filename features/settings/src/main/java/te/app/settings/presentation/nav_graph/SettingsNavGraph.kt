package te.app.settings.presentation.nav_graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import app.te.core.utils.enterTransition
import app.te.core.utils.exitTransition
import app.te.core.utils.popEnterTransition
import app.te.core.utils.popExitTransition
import app.te.settings.AboutAppNav
import app.te.settings.ContactUsNav
import app.te.settings.TermsNav
import te.app.settings.presentation.about_app.AboutAppScreen
import te.app.settings.presentation.contact_us.ContactUsScreen
import te.app.settings.presentation.contact_us.ContactUsViewModel
import te.app.settings.presentation.settings.SettingsViewModel
import te.app.settings.presentation.terms_privacy.TermsAndPrivacyScreen


fun NavGraphBuilder.settingsGraph(navHostController: NavHostController) {
    composable(
        AboutAppNav().destination,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
    ) {
        val viewModel = hiltViewModel<SettingsViewModel>()
        AboutAppScreen(viewModel)
    }
    composable(
        ContactUsNav().destination,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
    ) {
        val viewModel = hiltViewModel<ContactUsViewModel>()
        ContactUsScreen(navHostController, viewModel)
    }

    composable(
        TermsNav().route,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }, arguments = TermsNav().arguments
    ) {
        val viewModel = hiltViewModel<SettingsViewModel>()
        TermsAndPrivacyScreen(viewModel)
    }

}