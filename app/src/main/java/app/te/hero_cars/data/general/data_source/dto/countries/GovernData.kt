package app.te.hero_cars.data.general.data_source.dto.countries

import androidx.annotation.Keep
import app.te.hero_cars.domain.general.paginate.Links
import app.te.hero_cars.domain.general.paginate.Meta
import com.google.gson.annotations.SerializedName

@Keep
data class GovernData(
    @SerializedName("data")
    val government: List<Government>,
    val links: Links,
    val meta: Meta
)