package app.te.hero_cars.data.general.data_source.dto.countries

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Government(
    val id: Int = 0,
    val title: String = ""
) :Parcelable
