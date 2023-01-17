package app.te.architecture.presentation.contact_us.ui_state

import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.contact_us.ui_state.ContactUsUiState

data class ContactUsState(
    val data: List<ContactUsUiState>? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null
)
