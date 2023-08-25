package te.app.settings.presentation.more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.navigation.NavigationCommand
import app.te.navigation.NavigationManager
import app.te.navigation.NavigationOptions
import app.te.settings.AboutAppNav
import app.te.settings.ContactUsNav
import app.te.settings.RateAppNav
import app.te.settings.ShareAppNav
import app.te.settings.TermsNav
import te.app.settings.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val _moreListScreen = MutableStateFlow<List<MoreItem>>(emptyList())
    val moreListScreen = _moreListScreen.asStateFlow()

    var state = MutableSharedFlow<NavigationCommand>()

    init {
        initMoreListScreen()
    }

    private fun initMoreListScreen() {
        val listScreen = listOf(
            MoreItem(
                title = R.string.current_trip,
                icon = R.drawable.ic_history,
                route = AboutAppNav()
            ),
            MoreItem(
                title = R.string.trips_history,
                icon = R.drawable.ic_history,
                route = AboutAppNav()
            ),
            MoreItem(
                title = R.string.about,
                icon = R.drawable.about_app,
                route = AboutAppNav()
            ),

            MoreItem(
                title = R.string.terms,
                icon = R.drawable.terms,
                route = TermsNav(
                    NavigationOptions(
                        destination = TermsNav().passPageAndTitle(
                            page = "terms",
                            title = R.string.terms
                        )
                    )
                )
            ),
            MoreItem(
                title = R.string.privacy,
                icon = R.drawable.terms,
                route =
                TermsNav(
                    NavigationOptions(
                        destination = TermsNav().passPageAndTitle(
                            page = "privacy",
                            title = R.string.privacy
                        )
                    )
                )
            ),
            MoreItem(
                title = R.string.suggestions,
                icon = R.drawable.terms,
                route = ContactUsNav()
            ),
            MoreItem(
                title = R.string.rate_app,
                icon = R.drawable.rate_app,
                route = RateAppNav()
            ),
            MoreItem(
                title = R.string.share_app,
                icon = R.drawable.ic_share,
                route = ShareAppNav()
            )

        )
        _moreListScreen.value = listScreen
    }

    fun moreNavigation(route: NavigationCommand) {
        viewModelScope.launch {
            when (route) {
                is ShareAppNav -> state.emit(route)
                is RateAppNav -> state.emit(route)
                else -> navigationManager.navigate(route)
            }
        }
    }
}