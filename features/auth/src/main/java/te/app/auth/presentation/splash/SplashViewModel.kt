package te.app.auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.auth.AuthenticationDirections
import app.te.auth.SPLASH_ROUTE
import app.te.navigation.NavigationManager
import app.te.navigation.NavigationOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import te.app.auth.presentation.splash.state.SplashState
import te.app.storage.domain.use_case.CheckLoggedInUserUseCase
import te.app.storage.domain.use_case.GeneralUseCases
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val generalUseCases: GeneralUseCases,
    private val checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {
    private val _splashState =
        MutableStateFlow(SplashState())
    val splashState = _splashState.asStateFlow()

    fun checkFirstTime() {
        viewModelScope.launch {
            generalUseCases.checkFirstTimeUseCase().collect { isFirst ->
                delay(3000L)
                if (isFirst) {
                    openTutorialScreen()
                } else {
                    if (checkLoggedInUserUseCase.invoke())
                        openHomeActivity()
                    else
                        openUserType()


                }
            }
        }
    }

    private fun openTutorialScreen() {
        _splashState.value = SplashState(openTutorialScreen = true)
        viewModelScope.launch {
            navigationManager.navigate(
                AuthenticationDirections.OnBoardingScreen(
                    NavigationOptions(
                        popUpTo =
                        SPLASH_ROUTE
                    )
                )
            )
        }
    }

    private fun openHomeActivity() {
        _splashState.value = SplashState(openTutorialScreen = true)
        viewModelScope.launch {
            navigationManager.navigate(
                AuthenticationDirections.LoginScreenNav(
                    NavigationOptions(
                        popUpTo =
                        SPLASH_ROUTE
                    )
                )
            )
        }
    }

    private fun openUserType() {
        _splashState.value = SplashState(openTutorialScreen = true)
        viewModelScope.launch {
            navigationManager.navigate(
                AuthenticationDirections.UserTypeScreen(
                    NavigationOptions(
                        popUpTo =
                        SPLASH_ROUTE
                    )
                )
            )
        }
    }

}