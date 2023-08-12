package app.te.architecture.data.home.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Brand(
    val id: Int =0,
    val image: String="",
    val title: String=""
):Parcelable