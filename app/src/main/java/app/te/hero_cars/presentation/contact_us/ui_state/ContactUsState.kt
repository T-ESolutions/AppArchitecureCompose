package app.te.hero_cars.presentation.contact_us.ui_state

import app.te.network.utils.Resource

data class ContactUsState(
    val data: List<ContactUsUiState>? = null,
    val isLoading: Boolean = false,
    val failureStatus: app.te.network.utils.Resource.Failure? = null
)
