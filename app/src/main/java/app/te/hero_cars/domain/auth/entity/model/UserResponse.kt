package app.te.hero_cars.domain.auth.entity.model

import androidx.annotation.Keep
import app.te.hero_cars.data.general.data_source.dto.countries.CityModel
import com.google.gson.annotations.SerializedName

@Keep
data class UserResponse(
    @SerializedName("address")
  var address: String = "",

    @SerializedName("image")
  val image: String = "",

    @SerializedName("access_token")
  val accessToken: String = "",

    @SerializedName("city")
  val city: CityModel = CityModel(),

    @SerializedName("phone")
  val phone: String = "",

    @SerializedName("name")
  val name: String = "",

    @SerializedName("id")
  val id: Int = 0,

    @SerializedName("email")
  val email: String = "",

    )
