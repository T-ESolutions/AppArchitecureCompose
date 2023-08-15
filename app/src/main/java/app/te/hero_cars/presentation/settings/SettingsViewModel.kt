package app.te.hero_cars.presentation.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.hero_cars.R
import app.te.hero_cars.domain.settings.use_case.AboutUseCase
import app.te.network.utils.Resource
import app.te.hero_cars.presentation.more.nav_graph.TITLE_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
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
        savedStateHandle.get<Int>(TITLE_ARGUMENT)?.let { title ->
            this.title = title
        }
    }

    fun pages(page: String) {
        viewModelScope.launch {
            aboutUseCase.aboutData(page).collect { resource ->
                when (resource) {
                    is app.te.network.utils.Resource.Success -> {
                        _settingState.value = SettingsState(
                            data = resource.value.data.title
                        )
                    }
                    is app.te.network.utils.Resource.Failure -> {
                        _settingState.value = SettingsState(
                            failureStatus = resource
                        )
                    }
                    is app.te.network.utils.Resource.Loading -> {
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