package app.te.hero_cars.presentation.general.screens


const val GOVERNMENT_ROUTE = "government_screen"
const val CITIES_ROUTE = "cities_screen"
const val GOV_ID: String ="GOV_ID"

sealed class LocationsScreens(val route: String) {

    data object GovernmentScreen : LocationsScreens(route = GOVERNMENT_ROUTE)
    data object CitiesScreen : LocationsScreens(route = "$CITIES_ROUTE/{$GOV_ID}") {
        fun passGovId(govId: String): String = "$CITIES_ROUTE/$govId"
    }
}
