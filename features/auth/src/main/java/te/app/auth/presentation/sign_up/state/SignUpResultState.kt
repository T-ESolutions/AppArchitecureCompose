package te.app.auth.presentation.sign_up.state

import app.te.network.utils.Resource
import te.app.auth.domain.entity.model.UserResponse

data class SignUpResultState(
    val data: UserResponse? = null,
    val isLoading: Boolean = false,
    val failureStatus: Resource.Failure? = null,
    val msg: String = "",
)
