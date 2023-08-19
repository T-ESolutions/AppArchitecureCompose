package te.app.auth.presentation.sign_up.events

sealed class SignUpFormEvent {
    data class NameChanged(val name: String) : SignUpFormEvent()
    data class GenderChanged(val gender: String) : SignUpFormEvent()
    data class PhoneChanged(val phone: String) : SignUpFormEvent()
    data class IdentityChanged(val identity: String) : SignUpFormEvent()
    data class PasswordChanged(val password: String) : SignUpFormEvent()
    data object Back : SignUpFormEvent()
    data object Submit : SignUpFormEvent()
}
