package app.te.architecture.domain.settings.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AboutData(
  @SerializedName("body")
  val title: String = "",

  @SerializedName("image")
  val image: String = "",

  @SerializedName("id")
  val id: Int = 0,


  )
