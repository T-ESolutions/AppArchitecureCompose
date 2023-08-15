package app.te.hero_cars.presentation.home.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.hero_cars.domain.home.models.SerialSearchDM
import app.te.hero_cars.domain.home.use_case.HomeUseCase
import app.te.network.utils.Resource
import app.te.hero_cars.presentation.home.ui_state.HomeState
import app.te.hero_cars.presentation.home.ui_state.SearchState
import app.te.hero_cars.presentation.home.ui_state.StolenUiItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {
    private val _searchResponse =
        MutableStateFlow(HomeState())
    val searchResponse = _searchResponse
    var stateSearch by mutableStateOf(SearchState())

    fun searchForStolen() {
        if (stateSearch.serial.isNotEmpty())
            viewModelScope.launch {
                homeUseCase.searchForStolen(stateSearch.serial).collectLatest { resource ->

                    when (resource) {
                        is app.te.network.utils.Resource.Success -> {
                            _searchResponse.value = HomeState(
                                data = mapToSearchUiState(resource.value.data)
                            )
                        }
                        is app.te.network.utils.Resource.Failure -> {
                            _searchResponse.value = HomeState(
                                failureStatus = resource
                            )
                        }
                        is app.te.network.utils.Resource.Loading -> {
                            _searchResponse.value = HomeState(
                                isLoading = true
                            )
                        }
                        else -> {}
                    }
                }
            }
    }

    private fun mapToSearchUiState(data: SerialSearchDM): List<StolenUiItemState> {
        val stolenPhone = mutableListOf<StolenUiItemState>()
        stolenPhone.add(StolenUiItemState(data))
        return stolenPhone
    }

    fun onSerialChange(serial: String) {
        stateSearch = stateSearch.copy(serial = serial)
    }
}