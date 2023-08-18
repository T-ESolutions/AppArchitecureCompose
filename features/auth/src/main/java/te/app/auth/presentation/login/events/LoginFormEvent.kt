package te.app.auth.presentation.login.events

sealed class LoginFormEvent {
    data class PhoneChanged(val phone: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()
    data object ResetPassword : LoginFormEvent()
    data object LoginFaceBook : LoginFormEvent()
    data object LoginGoogle : LoginFormEvent()
    data object SignUp : LoginFormEvent()
    data object Login : LoginFormEvent()
}
