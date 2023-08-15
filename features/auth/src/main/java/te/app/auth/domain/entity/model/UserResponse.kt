package te.app.auth.domain.entity.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserResponse(
    @SerializedName("address")
  var address: String = "",

    @SerializedName("image")
  val image: String = "",

    @SerializedName("access_token")
  val accessToken: String = "",


    @SerializedName("phone")
  val phone: String = "",

    @SerializedName("name")
  val name: String = "",

    @SerializedName("id")
  val id: Int = 0,

    @SerializedName("email")
  val email: String = "",

    )
