package app.te.architecture.data.brand_model.dto

import androidx.annotation.Keep
import app.te.architecture.data.home.dto.Brand
import app.te.architecture.domain.general.paginate.Links
import app.te.architecture.domain.general.paginate.Meta
import com.google.gson.annotations.SerializedName

@Keep
data class BrandsDto(
    @SerializedName("data")
    val brand: List<Brand>,
    val links: Links,
    val meta: Meta
)