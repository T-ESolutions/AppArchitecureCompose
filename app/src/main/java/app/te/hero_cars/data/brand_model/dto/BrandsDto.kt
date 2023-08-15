package app.te.hero_cars.data.brand_model.dto

import androidx.annotation.Keep
import app.te.hero_cars.data.home.dto.Brand
import app.te.hero_cars.domain.general.paginate.Links
import app.te.hero_cars.domain.general.paginate.Meta
import com.google.gson.annotations.SerializedName

@Keep
data class BrandsDto(
    @SerializedName("data")
    val brand: List<Brand>,
    val links: Links,
    val meta: Meta
)