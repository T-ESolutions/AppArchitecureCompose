package app.te.hero_cars.domain.auth.entity.request

import androidx.annotation.Keep

@Keep
data class LogInRequest(
    val phone: String = "",
    val password: String = "",
)
