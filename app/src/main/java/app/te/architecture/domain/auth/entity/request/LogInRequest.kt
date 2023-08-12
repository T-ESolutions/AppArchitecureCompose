package app.te.architecture.domain.auth.entity.request

import androidx.annotation.Keep

@Keep
data class LogInRequest(
    val phone: String = "",
    val password: String = "",
)
