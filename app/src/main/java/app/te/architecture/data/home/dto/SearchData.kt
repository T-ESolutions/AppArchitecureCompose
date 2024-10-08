package app.te.architecture.data.home.dto

import androidx.annotation.Keep
import app.te.architecture.data.general.data_source.dto.countries.CityModel
import app.te.architecture.domain.auth.entity.model.UserResponse

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