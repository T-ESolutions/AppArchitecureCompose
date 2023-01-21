package app.te.architecture.presentation.auth.sign_up.state

import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.utils.Resource

data class SignUpResultState(
    val data: UserResponse? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null,
    val msg: String = "",
)
