package app.te.hero_cars.presentation.settings

import app.te.network.utils.Resource

data class SettingsState(
    val data: String? = null,
    val isLoading: Boolean = false,
    val failureStatus: app.te.network.utils.Resource.Failure? = null
)
