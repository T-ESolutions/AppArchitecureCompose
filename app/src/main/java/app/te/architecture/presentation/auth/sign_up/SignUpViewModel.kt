package app.te.architecture.presentation.auth.sign_up

import androidx.lifecycle.viewModelScope
import app.te.architecture.domain.auth.use_case.RegisterUseCase
import app.te.architecture.domain.general.entity.countries.CityModel
import app.te.architecture.domain.general.use_case.CitiesUseCase
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val cityUseCases: CitiesUseCase
) :
    BaseViewModel() {
    lateinit var registerUiState: RegisterUiState
    private val _registerResponse = MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
    val registerResponse = _registerResponse

    private val _citiesResponse =
        MutableStateFlow<Resource<BaseResponse<List<CityModel>>>>(Resource.Default)
    val citiesResponse = _citiesResponse
    var cities: List<CityModel> = listOf()

    init {
        getCities()
    }

    fun register() {
        registerUseCase(registerUiState.request)
            .catch { exception -> }
            .onEach { result ->
                _registerResponse.value = result
            }
            .launchIn(viewModelScope)
    }

    fun getCities() {
        cityUseCases()
            .catch { exception -> }
            .onEach { result ->
                _citiesResponse.value = result
            }
            .launchIn(viewModelScope)
    }
}