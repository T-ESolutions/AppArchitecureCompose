package app.te.hero_cars.presentation.auth.login.events

sealed class LoginFormEvent {
    data class PhoneChanged(val phone: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()
    object Submit : LoginFormEvent()
}
