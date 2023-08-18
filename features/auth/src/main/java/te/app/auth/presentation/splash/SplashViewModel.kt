package te.app.auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.auth.AuthenticationDirections
import app.te.auth.SPLASH_ROUTE
import app.te.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import te.app.auth.presentation.splash.state.SplashState
import te.app.storage.domain.use_case.GeneralUseCases
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val generalUseCases: GeneralUseCases,
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
                    openTutorialScreen()
                }
            }
        }
    }

    private fun openTutorialScreen() {
        _splashState.value = SplashState(openTutorialScreen = true)
        navigationManager.navigate(AuthenticationDirections.OnBoardingScreen(SPLASH_ROUTE))
    }

    private fun openHomeActivity() {
        _splashState.value = SplashState(openTutorialScreen = false)
        navigationManager.navigate(AuthenticationDirections.LoginScreenNav(SPLASH_ROUTE))
    }
}