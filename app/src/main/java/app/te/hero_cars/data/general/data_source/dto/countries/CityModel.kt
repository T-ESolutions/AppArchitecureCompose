package app.te.hero_cars.data.general.data_source.dto.countries

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class CityModel(
  @SerializedName("image")
  var image: String = "",

  @SerializedName("title")
  val name: String = "",

  @SerializedName("id")
  val id: Int = 0,
  val gov: Government = Government()
): Parcelable

