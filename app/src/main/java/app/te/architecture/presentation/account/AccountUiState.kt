package app.te.architecture.presentation.account

import app.te.architecture.R
import app.te.architecture.domain.utils.Resource
import com.structure.base_mvvm.User

class AccountUiState {
    val isLoading: Boolean = false
    val failureStatus: Resource.Failure? = null

    var user: User = User.getDefaultInstance()
    fun updateUi(user: User) {
        this.user = user
    }

    fun getLogUser(): Int =
        if (user.name.isNotEmpty()) R.string.log_out else R.string.login

    fun checkLogAction(onLoginAction: () -> Unit, onLoginOutAction: () -> Unit) {
        if (user.name.isNotEmpty()) onLoginOutAction() else onLoginAction()
    }
}