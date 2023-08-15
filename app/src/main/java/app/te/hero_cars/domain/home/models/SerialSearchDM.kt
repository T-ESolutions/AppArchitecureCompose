package app.te.hero_cars.domain.home.models

import app.te.hero_cars.domain.auth.entity.model.UserResponse

data class SerialSearchDM(
    val added_by: UserResponse? = null,
    val id: Int = 0,
    val image: String = "",
    val notes: String = "",
    val serial: String = "",
    val modell_name: String = "",
    val brand_name: String = "",
    val city_name: String = "",
    val gov_name: String = "",
    val phone: String? = null
)
