package te.app.auth.presentation.login.state

import app.te.hero_cars.domain.auth.entity.model.UserResponse
import app.te.network.utils.Resource

data class LoginState(
    val data: UserResponse? = null,
    val isLoading: Boolean = false,
    val failureStatus: app.te.network.utils.Resource.Failure? = null
)
