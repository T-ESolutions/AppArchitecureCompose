package app.te.hero_cars.domain.account.entity.request

import androidx.annotation.Keep

@Keep
data class SendFirebaseTokenRequest(
 var firebase_token: String,
 var platform: String,
 val device_id: String
)