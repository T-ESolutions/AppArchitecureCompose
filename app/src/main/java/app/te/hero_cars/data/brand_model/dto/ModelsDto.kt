package app.te.hero_cars.data.brand_model.dto

import androidx.annotation.Keep
import app.te.hero_cars.data.home.dto.Model
import app.te.hero_cars.domain.general.paginate.Links
import app.te.hero_cars.domain.general.paginate.Meta
import com.google.gson.annotations.SerializedName

@Keep
data class ModelsDto(
    @SerializedName("data")
    val models: List<Model>,
    val links: Links,
    val meta: Meta
)