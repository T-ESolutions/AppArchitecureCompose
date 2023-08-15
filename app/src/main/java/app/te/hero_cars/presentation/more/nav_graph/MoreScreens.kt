package app.te.hero_cars.presentation.more.nav_graph

const val MORE_GRAPH_ROUTE = "more_root"
const val ABOUT_ROUTE = "about_screen"
const val CONTACT_US_ROUTE = "contact_us_screen"
const val TERMS_ROUTE = "terms_screen"
const val PAGE_ARGUMENT = "page"
const val TITLE_ARGUMENT = "title"

sealed class MoreScreens(
    val route: String
) {
    object About : MoreScreens(
        route = ABOUT_ROUTE
    )

    object ContactUs : MoreScreens(
        route = CONTACT_US_ROUTE
    )

    object TermsAndPrivacy : MoreScreens(
        route = "$TERMS_ROUTE/{$PAGE_ARGUMENT}/{$TITLE_ARGUMENT}"
    ) {
        fun passPageAndTitle(page: String, title: Int): String = "$TERMS_ROUTE/$page/$title"
    }

}
