package app.te.architecture.presentation.settings

import app.te.architecture.domain.utils.Resource

data class SettingsState(
    val data: String? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null
)
