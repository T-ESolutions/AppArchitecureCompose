package app.te.architecture.presentation.more

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.lifecycle.ViewModel
import app.te.architecture.R
import app.te.architecture.presentation.more.nav_graph.ABOUT_ROUTE
import app.te.architecture.presentation.more.nav_graph.CONTACT_US_ROUTE
import app.te.architecture.presentation.more.nav_graph.MoreScreens
import app.te.architecture.presentation.more.nav_graph.TERMS_ROUTE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor() : ViewModel() {

    private val _moreListScreen = MutableStateFlow<List<MoreItem>>(emptyList())
    val moreListScreen = _moreListScreen.asStateFlow()

    fun initMoreListScreen() {
        val listScreen = listOf(
            MoreItem(title = R.string.about, icon = Icons.Filled.Apps, route = ABOUT_ROUTE),
            MoreItem(
                title = R.string.contact_us,
                icon = Icons.Filled.PeopleAlt,
                route = CONTACT_US_ROUTE
            ),
            MoreItem(
                title = R.string.terms,
                icon = Icons.Filled.Assessment,
                route = MoreScreens.TermsAndPrivacy.passPageAndTitle(
                    page = "terms",
                    title = R.string.terms
                )
            ),
            MoreItem(
                title = R.string.privacy,
                icon = Icons.Filled.PrivacyTip,
                route = MoreScreens.TermsAndPrivacy.passPageAndTitle(
                    page = "privacy",
                    title = R.string.privacy
                )
            ),
        )
        _moreListScreen.value = listScreen
    }
}