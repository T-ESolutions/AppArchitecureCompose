package app.te.hero_cars.presentation.add_stolen_phone.states

import app.te.network.utils.Resource

data class AddStolenResultState(
    val data: Any? = null,
    val isLoading: Boolean = false,
    val failureStatus: app.te.network.utils.Resource.Failure? = null,
    val msg: String = "",
)
