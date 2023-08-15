package te.app.auth.domain.entity.request

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ForgetPasswordRequest(
    var phone: String = "",
    var code: String = "",
    var city_id: String? = null,
    var email: String? = null,

    ) : Parcelable