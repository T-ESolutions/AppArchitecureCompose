package app.te.hero_cars.presentation.auth.sign_up.state

import app.te.hero_cars.domain.auth.entity.model.UserResponse
import app.te.network.utils.Resource

data class SignUpResultState(
    val data: UserResponse? = null,
    val isLoading: Boolean = false,
    val failureStatus: app.te.network.utils.Resource.Failure? = null,
    val msg: String = "",
)
