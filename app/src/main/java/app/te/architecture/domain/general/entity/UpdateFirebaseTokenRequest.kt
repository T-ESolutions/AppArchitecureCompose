package app.te.architecture.domain.general.entity

import androidx.annotation.Keep
import app.te.architecture.data.remote.Keys

@Keep
data class UpdateFirebaseTokenRequest(
    var fcm_token: String = "",
    var platform: String = Keys.platForm()
)
