package te.app.settings.presentation.more

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.lifecycle.ViewModel
import app.te.settings.AboutAppNav
import app.te.settings.ContactUsNav
import app.te.settings.TermsAndPrivacyNav
import te.app.settings.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor() : ViewModel() {

    private val _moreListScreen = MutableStateFlow<List<MoreItem>>(emptyList())
    val moreListScreen = _moreListScreen.asStateFlow()

    init {
        initMoreListScreen()
    }

    private fun initMoreListScreen() {
        val listScreen = listOf(
            MoreItem(
                title = R.string.current_trip,
                icon = R.drawable.ic_history,
                route = AboutAppNav().destination
            ),
            MoreItem(
                title = R.string.trips_history,
                icon = R.drawable.ic_history,
                route = AboutAppNav().destination
            ),
            MoreItem(
                title = R.string.about,
                icon = R.drawable.about_app,
                route = AboutAppNav().destination
            ),

            MoreItem(
                title = R.string.terms,
                icon = R.drawable.terms,
                route = TermsAndPrivacyNav().passPageAndTitle(
                    page = "terms",
                    title = R.string.terms
                )
            ),
            MoreItem(
                title = R.string.privacy,
                icon = R.drawable.terms,
                route = TermsAndPrivacyNav().passPageAndTitle(
                    page = "privacy",
                    title = R.string.privacy
                )
            ),
            MoreItem(
                title = R.string.suggestions,
                icon = R.drawable.terms,
                route = ContactUsNav().destination
            ),
            MoreItem(
                title = R.string.rate_app,
                icon = R.drawable.rate_app,
                route = ContactUsNav().destination
            ),
            MoreItem(
                title = R.string.share_app,
                icon = R.drawable.ic_share,
                route = ContactUsNav().destination
            )

        )
        _moreListScreen.value = listScreen
    }
}