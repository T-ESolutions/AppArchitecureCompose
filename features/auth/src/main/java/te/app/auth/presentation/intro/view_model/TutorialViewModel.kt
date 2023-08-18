package te.app.auth.presentation.intro.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.auth.AuthenticationDirections
import app.te.navigation.NavigationManager
import app.te.network.utils.Resource
import te.app.auth.presentation.intro.state.IntroState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import te.app.auth.domain.use_case.IntroUseCase
import te.app.storage.domain.use_case.GeneralUseCases
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val generalUseCases: GeneralUseCases,
    private val introUseCase: IntroUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {
    private val _appTutorialState =
        MutableStateFlow(IntroState())
    val appTutorialState = _appTutorialState.asStateFlow()

    fun getIntro() {
        viewModelScope.launch {
            introUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _appTutorialState.value = IntroState(
                            data = resource.value.data
                        )
                        setFirstTime()
                    }

                    is Resource.Failure -> {
                        _appTutorialState.value = IntroState(
                            failureStatus = resource
                        )
                    }

                    is Resource.Loading -> {
                        _appTutorialState.value = IntroState(
                            isLoading = true
                        )
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setFirstTime() {
        viewModelScope.launch {
            generalUseCases.setFirstTimeUseCase(false)
        }
    }

    fun openLogin() {
        navigationManager.navigate(AuthenticationDirections.LoginScreenNav(AuthenticationDirections.OnBoardingScreen().destination))
    }
}