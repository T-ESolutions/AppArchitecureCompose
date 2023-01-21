package app.te.architecture.domain.add_stolen_phone.entity

import app.te.architecture.domain.utils.BaseRequest
import com.google.gson.annotations.SerializedName

data class AddStolenPhoneRequest(
    @SerializedName("brand_id")
    var brandId: String = "",
    @SerializedName("modell_id")
    var modelId: String = "",

    @SerializedName("serial")
    var serial: String = "",

    @SerializedName("gov_id")
    var govId: String = "",

    @SerializedName("city_id")
    var cityId: String = "",

    @SerializedName("notes")
    var notes: String = "",

    @SerializedName("phone")
    var phone: String = "",
) : BaseRequest()

