package app.te.architecture.presentation.auth.intro.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.architecture.presentation.auth.intro.state.IntroState
import app.te.architecture.domain.general.use_case.GeneralUseCases
import app.te.architecture.domain.intro.use_case.IntroUseCase
import app.te.architecture.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor(
    private val generalUseCases: GeneralUseCases,
    private val introUseCase: IntroUseCase
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
                        Log.e("getIntro", "getIntro: "+resource.code)
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

}