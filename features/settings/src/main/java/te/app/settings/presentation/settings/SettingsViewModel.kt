package te.app.settings.presentation.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.network.utils.Resource
import app.te.settings.TermsNav
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import te.app.settings.R
import te.app.settings.domain.use_case.AboutUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val aboutUseCase: AboutUseCase,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _settingState =
        MutableStateFlow(SettingsState())
    val settingState = _settingState
    var title: Int = R.string.privacy

    init {
        savedStateHandle.get<Int>(TermsNav().TITLEARGUMENT)?.let { title ->
            this.title = title
        }
    }

    fun pages(page: String) {
        viewModelScope.launch {
            aboutUseCase.aboutData(page).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _settingState.value = SettingsState(
                            data = resource.value.data.title
                        )
                    }

                    is Resource.Failure -> {
                        _settingState.value = SettingsState(
                            failureStatus = resource
                        )
                    }

                    is Resource.Loading -> {
                        _settingState.value = SettingsState(
                            isLoading = true
                        )
                    }

                    else -> {}
                }

            }
        }
    }

}