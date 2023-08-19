package te.app.auth.presentation.user_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.auth.AuthenticationDirections
import app.te.auth.USER_TYPE_ROUTE
import app.te.navigation.NavigationManager
import app.te.navigation.NavigationOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import te.app.storage.domain.entity.UserType
import te.app.storage.domain.use_case.UserTypeUseCase
import javax.inject.Inject

@HiltViewModel
class CheckUserTypeViewModel @Inject constructor(
    private val userTypeUseCase: UserTypeUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun openLogin(selection: Boolean) {
        viewModelScope.launch {
            userTypeUseCase.invoke(if (selection) UserType.CLIENT.userType else UserType.CAR_OWNER.userType)
            navigationManager.navigate(
                AuthenticationDirections.LoginScreenNav(
                    NavigationOptions(
                        popUpTo = USER_TYPE_ROUTE
                    )
                )
            )
        }
    }


}