package te.app.auth.domain.entity.request

import androidx.annotation.Keep

@Keep
data class RegisterRequest(

    val phone: String = "",

    val gender: String = "",

    val name: String = "",

    val password: String = "",
)
