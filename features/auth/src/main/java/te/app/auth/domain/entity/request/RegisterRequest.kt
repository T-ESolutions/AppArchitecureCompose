package te.app.auth.domain.entity.request

import androidx.annotation.Keep

@Keep
data class RegisterRequest(

    val phone: String = "",

    val address: String = "",

    val city_id: String = "",

    val name: String = "",

    val gov_id: String = "",

    val password: String = "",
)
