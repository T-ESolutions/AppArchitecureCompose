package app.te.architecture.domain.utils

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BaseResponse<T>(
  var data: T,
  @SerializedName("msg")
  val message: String,
  val status: Int,
)