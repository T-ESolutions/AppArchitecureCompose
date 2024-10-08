package app.te.architecture.presentation.add_stolen_phone.states

data class AddStolenFormState(
    val govern: String = "",
    val governId: String = "",
    val governError: String? = null,
    val city: String = "",
    val cityId: String = "",
    val cityError: String? = null,
    val brand: String = "",
    val brandId: String = "",
    val brandError: String? = null,
    val model: String = "",
    val modelId: String = "",
    val modelError: String? = null,
    val phone: String = "",
    val phoneError: String? = null,
    val serial: String = "",
    val serialError: String? = null,
    val image: String = "",
    val imageText: Int? = null,
    val notes: String = "",
    val notesError: String? = null,
    val checkBoxChecked: Boolean = false,
    val checkBoxCheckedError: Int? = null,
)
