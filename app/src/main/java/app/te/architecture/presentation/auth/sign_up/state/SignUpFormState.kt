package app.te.architecture.presentation.auth.sign_up.state

data class SignUpFormState(
    val govern: String = "",
    val governId: String = "",
    val governError: String? = null,
    val city: String = "",
    val cityId: String = "",
    val cityError: String? = null,
    val name: String = "",
    val nameError: String? = null,
    val phone: String = "",
    val phoneError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val address: String = "",
    val addressError: String? = null,
)
