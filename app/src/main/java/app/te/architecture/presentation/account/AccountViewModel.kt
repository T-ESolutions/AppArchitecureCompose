package app.te.architecture.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.architecture.domain.account.use_case.AccountUseCases
import app.te.architecture.domain.account.use_case.UserLocalUseCase
import app.te.architecture.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases,
    private val userLocalUseCase: UserLocalUseCase
) : ViewModel() {

    private val _userData = MutableStateFlow(AccountUiState())
    val userData = _userData.asStateFlow()


    fun getUserFromLocal() {
        viewModelScope.launch {
            userLocalUseCase.invoke().collect {
                val uiState = AccountUiState()
                uiState.updateUi(it)
                _userData.value = uiState
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            accountUseCases.logOutUseCase().collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {

                    }
                    else -> {}
                }

            }
        }

    }

}