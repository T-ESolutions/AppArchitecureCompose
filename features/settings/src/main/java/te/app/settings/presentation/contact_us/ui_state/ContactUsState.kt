package te.app.settings.presentation.contact_us.ui_state

import app.te.network.utils.Resource

data class ContactUsState(
    val data: List<ContactUsUiState>? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null
)
