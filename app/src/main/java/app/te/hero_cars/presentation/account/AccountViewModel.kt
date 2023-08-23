package app.te.hero_cars.presentation.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.auth.LoginNav
import app.te.bottom_bar.BottomBarNav
import app.te.hero_cars.domain.account.use_case.AccountUseCases
import app.te.navigation.NavigationManager
import app.te.navigation.NavigationOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases,
    private val navigationManager: NavigationManager
//    private val userLocalUseCase: UserLocalUseCase
) : ViewModel() {

    private val _userData = MutableStateFlow(AccountUiState())
    val userData = _userData.asStateFlow()


    fun getUserFromLocal() {
//        viewModelScope.launch {
//            userLocalUseCase.invoke().collect {
//                val uiState = AccountUiState()
//                uiState.updateUi(it)
//                _userData.value = uiState
//            }
//        }
    }

    fun logOut() {
        viewModelScope.launch {
//            accountUseCases.logOutUseCase().collectLatest { resource ->
//                when (resource) {
//                    is app.te.network.utils.Resource.Success -> {
//
//                    }
//                    else -> {}
//                }
//
//            }
            navigationManager.navigate(LoginNav(NavigationOptions(popUpToId = BottomBarNav().destination)))
        }

    }

}