package app.te.architecture.presentation.auth.login.state

import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.utils.Resource

data class LoginState(
    val data: UserResponse? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null
)
