package app.te.hero_cars.domain.general.entity

import androidx.annotation.Keep

@Keep
data class UpdateFirebaseTokenRequest(
    var fcm_token: String = "",
    var platform: String = te.app.storage.Keys.platForm()
)
