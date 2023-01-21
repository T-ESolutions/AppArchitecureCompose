package app.te.architecture.presentation.general.screens

import app.te.architecture.presentation.auth.nav_graph.GOV_ID

const val GOVERNMENT_ROUTE = "government_screen"
const val CITIES_ROUTE = "cities_screen"

sealed class LocationsScreens(val route: String) {
    object GovernmentScreen : LocationsScreens(route = GOVERNMENT_ROUTE)
    object CitiesScreen : LocationsScreens(route = "$CITIES_ROUTE/{$GOV_ID}") {
        fun passGovId(govId: String): String = "$CITIES_ROUTE/$govId"
    }
}
