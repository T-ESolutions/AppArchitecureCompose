package app.te.hero_cars.data.home.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Model(
    val brand: Brand = Brand(),
    val id: Int =0,
    val image: String ="",
    val title: String =""
): Parcelable