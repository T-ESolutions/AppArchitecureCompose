package app.te.hero_cars.presentation.general.screens


const val BRANDS_ROUTE = "brands_screen"
const val MODELS_ROUTE = "models_screen"
const val BRAND_ID = "brand_id"

sealed class BrandModelScreens(val route: String) {
    object BrandsScreen : BrandModelScreens(route = BRANDS_ROUTE)
    object ModelsScreen : BrandModelScreens(route = "$MODELS_ROUTE/{$BRAND_ID}") {
        fun passBrandId(brandId: String): String = "$MODELS_ROUTE/$brandId"
    }
}
