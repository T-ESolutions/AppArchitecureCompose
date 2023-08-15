package te.app.auth.domain.entity.request

import androidx.annotation.Keep

@Keep
data class LogInRequest(
    val phone: String = "",
    val password: String = "",
)
