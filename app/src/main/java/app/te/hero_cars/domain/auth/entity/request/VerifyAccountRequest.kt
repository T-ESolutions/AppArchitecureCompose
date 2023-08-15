package app.te.hero_cars.domain.auth.entity.request

import androidx.annotation.Keep

@Keep
data class VerifyAccountRequest(
  var type: String = "",
  var phone: String = "",
  var code: String = ""
)
