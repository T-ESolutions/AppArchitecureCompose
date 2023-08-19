package te.app.auth.presentation.login.state

data class LoginFormState(
    val phone: String = "",
    val phoneError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)
