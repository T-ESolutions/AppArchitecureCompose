package app.te.hero_cars.presentation.home.ui_state

import app.te.network.utils.Resource

data class HomeState(
    val data: List<StolenUiItemState>? = null,
    val isLoading: Boolean = false,
    val failureStatus: app.te.network.utils.Resource.Failure? = null
)
