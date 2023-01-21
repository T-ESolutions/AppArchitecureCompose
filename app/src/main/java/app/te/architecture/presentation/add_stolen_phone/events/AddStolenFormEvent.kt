package app.te.architecture.presentation.add_stolen_phone.events

sealed class AddStolenFormEvent {
    data class GovernChanged(val govern: String) : AddStolenFormEvent()
    data class CityChanged(val city: String) : AddStolenFormEvent()
    data class BrandChanged(val brand: String) : AddStolenFormEvent()
    data class ModelChanged(val model: String) : AddStolenFormEvent()
    data class PhoneChanged(val phone: String) : AddStolenFormEvent()
    data class SerialChanged(val serial: String) : AddStolenFormEvent()
    data class ImageChanged(val image: String) : AddStolenFormEvent()
    data class NotesChanged(val notes: String) : AddStolenFormEvent()
    data class CheckBoxChanged(val checkBoxChecked: Boolean) : AddStolenFormEvent()
    object Submit : AddStolenFormEvent()
}
