package app.te.hero_cars.data.home.dto

import androidx.annotation.Keep
import app.te.hero_cars.data.general.data_source.dto.countries.CityModel
import app.te.hero_cars.domain.auth.entity.model.UserResponse

@Keep
data class SearchData(
    val added_by: UserResponse? = null,
    val city: CityModel = CityModel(),
    val id: Int = 0,
    val image: String = "",
    val modell: Model,
    val notes: String = "",
    val serial: String = "",
    val modell_name: String = "",
    val brand_name: String = "",
    val city_name: String = "",
    val gov_name: String = "",
    val phone: String? = null
)