package app.te.architecture.domain.settings.models

import androidx.annotation.Keep

@Keep
data class ContactUs(
    val value: String = "",
    val key: String = "",
    val image: String = "",
    val id: Int = 0,
)
