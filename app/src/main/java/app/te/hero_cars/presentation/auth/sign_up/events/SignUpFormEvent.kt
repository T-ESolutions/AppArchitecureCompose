package app.te.hero_cars.presentation.auth.sign_up.events

sealed class SignUpFormEvent {
    data class GovernChanged(val govern: String) : SignUpFormEvent()
    data class CityChanged(val city: String) : SignUpFormEvent()
    data class NameChanged(val name: String) : SignUpFormEvent()
    data class AddressChanged(val address: String) : SignUpFormEvent()
    data class PhoneChanged(val phone: String) : SignUpFormEvent()
    data class PasswordChanged(val password: String) : SignUpFormEvent()
    object Submit : SignUpFormEvent()
}
