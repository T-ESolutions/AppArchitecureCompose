package te.app.auth.presentation.sign_up.state

data class SignUpFormState(
    val name: String = "",
    val nameError: String? = null,
    val phone: String = "",
    val phoneError: String? = null,
    val identity: String = "",
    val identityError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val gender: String = "",
)
