package app.te.architecture.data.general.data_source.dto.countries

import androidx.annotation.Keep
import app.te.architecture.domain.general.paginate.Links
import app.te.architecture.domain.general.paginate.Meta
import com.google.gson.annotations.SerializedName

@Keep
data class GovernData(
    @SerializedName("data")
    val government: List<Government>,
    val links: Links,
    val meta: Meta
)