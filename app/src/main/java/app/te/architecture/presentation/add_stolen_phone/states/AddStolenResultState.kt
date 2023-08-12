package app.te.architecture.presentation.add_stolen_phone.states

import app.te.architecture.domain.utils.Resource

data class AddStolenResultState(
    val data: Any? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null,
    val msg: String = "",
)
