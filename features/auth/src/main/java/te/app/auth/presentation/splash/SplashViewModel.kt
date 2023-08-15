package te.app.auth.presentation.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import te.app.auth.presentation.splash.state.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import te.app.storage.domain.use_case.GeneralUseCases
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val generalUseCases: GeneralUseCases
) : ViewModel() {
    private val _splashState =
        MutableStateFlow(SplashState())
    val splashState = _splashState.asStateFlow()

    fun checkFirstTime() {
        viewModelScope.launch {
            generalUseCases.checkFirstTimeUseCase().collect { isFirst ->
                if (isFirst) {
                    _splashState.value = SplashState(openTutorialScreen = true)
                } else {
                    _splashState.value = SplashState(openTutorialScreen = false)
                }
            }
        }
    }

}