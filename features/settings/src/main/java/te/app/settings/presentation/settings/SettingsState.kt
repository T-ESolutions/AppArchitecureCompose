package te.app.settings.presentation.settings

import app.te.network.utils.Resource

data class SettingsState(
    val data: String? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null
)
