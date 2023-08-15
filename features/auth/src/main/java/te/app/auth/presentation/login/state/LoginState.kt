package te.app.auth.presentation.login.state

import app.te.network.utils.Resource
import te.app.auth.domain.entity.model.UserResponse

data class LoginState(
    val data: UserResponse? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null
)
