package te.app.auth.presentation.locations.view_model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import te.app.auth.presentation.nav_graph.GOV_ID
import app.te.hero_cars.data.general.data_source.dto.countries.CityModel
import app.te.hero_cars.data.general.data_source.dto.countries.Government
import app.te.hero_cars.domain.general.use_case.CitiesUseCase
import app.te.hero_cars.domain.general.use_case.GovernmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val citiesUseCase: CitiesUseCase,
    private val governmentsUseCase: GovernmentsUseCase,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _citiesState =
        MutableStateFlow<PagingData<CityModel>>(PagingData.empty())
    val citiesState = _citiesState

    private val _governmentState =
        MutableStateFlow<PagingData<Government>>(PagingData.empty())
    val governmentState = _governmentState

    init {
        savedStateHandle.get<String>(GOV_ID)?.let {
            Log.e("savedStateHandle", ": " + it)
        }
    }

    fun getGovernments() {
        viewModelScope.launch {
            governmentsUseCase.invoke()
                .collect { result ->
                    _governmentState.value = result
                }
        }
    }


    fun getCitiesByGovern(governId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            citiesUseCase.invoke(governId)
                .collect { result ->
                    _citiesState.value = result
                }
        }

    }

}