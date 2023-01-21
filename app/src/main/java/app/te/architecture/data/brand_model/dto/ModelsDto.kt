package app.te.architecture.data.brand_model.dto

import androidx.annotation.Keep
import app.te.architecture.data.home.dto.Model
import app.te.architecture.domain.general.paginate.Links
import app.te.architecture.domain.general.paginate.Meta
import com.google.gson.annotations.SerializedName

@Keep
data class ModelsDto(
    @SerializedName("data")
    val models: List<Model>,
    val links: Links,
    val meta: Meta
)