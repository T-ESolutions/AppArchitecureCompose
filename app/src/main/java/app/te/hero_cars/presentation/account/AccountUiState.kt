package app.te.hero_cars.presentation.account

import app.te.network.utils.Resource
import com.structure.base_mvvm.User

class AccountUiState {
    val isLoading: Boolean = false
    val failureStatus: Resource.Failure? = null

    var user: User = User.getDefaultInstance()
    fun updateUi(user: User) {
        this.user = user
    }

    fun getLogUser(): Int =
        if (user.name.isNotEmpty()) app.te.core.R.string.log_out else te.app.auth.R.string.login

    fun checkLogAction(onLoginAction: () -> Unit, onLoginOutAction: () -> Unit) {
        if (user.name.isNotEmpty()) onLoginOutAction() else onLoginAction()
    }
}