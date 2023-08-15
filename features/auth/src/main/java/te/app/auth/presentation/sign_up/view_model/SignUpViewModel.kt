package te.app.auth.presentation.sign_up.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import te.app.auth.presentation.sign_up.events.SignUpFormEvent
import te.app.auth.presentation.sign_up.state.SignUpFormState
import te.app.auth.presentation.sign_up.state.SignUpResultState
import app.te.core.validation_usecase.ValidateField
import app.te.core.validation_usecase.ValidatePassword
import app.te.core.validation_usecase.ValidatePhone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import te.app.auth.domain.entity.request.RegisterRequest
import te.app.auth.domain.use_case.RegisterUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validatePhone: ValidatePhone,
    private val validatePassword: ValidatePassword,
    private val validateField: ValidateField
) : ViewModel() {

    var state by mutableStateOf(SignUpFormState())
    private val _signUpState =
        MutableStateFlow(SignUpResultState())
    val signUpState = _signUpState.asStateFlow()


    fun onEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.GovernChanged -> {
                state = state.copy(
                    govern = event.govern,
                    governError = validateField.invoke(event.govern).errorMessage
                )
            }
            is SignUpFormEvent.CityChanged -> {
                state = state.copy(
                    city = event.city,
                    cityError = validateField.invoke(event.city).errorMessage
                )
            }
            is SignUpFormEvent.NameChanged -> {
                state = state.copy(
                    name = event.name,
                    nameError = validateField.invoke(event.name).errorMessage
                )
            }
            is SignUpFormEvent.PhoneChanged -> {
                state = state.copy(
                    phone = event.phone,
                    phoneError = validatePhone.invoke(event.phone).errorMessage
                )
            }

            is SignUpFormEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password,
                    passwordError = validatePassword.invoke(event.password).errorMessage
                )
            }
            is SignUpFormEvent.AddressChanged -> {
                state = state.copy(
                    address = event.address,
                    addressError = validateField.invoke(event.address).errorMessage
                )
            }
            is SignUpFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        if (validateInputs())
            viewModelScope.launch {
                registerUseCase.invoke(
                    RegisterRequest(
                        gov_id = state.governId,
                        city_id = state.cityId,
                        name = state.name,
                        phone = state.phone,
                        password = state.password,
                        address = state.address,
                    )
                )
                    .collect { resource ->
                        when (resource) {
                            is app.te.network.utils.Resource.Success -> {
                                _signUpState.value = SignUpResultState(
                                    data = resource.value.data,
                                    msg = resource.value.message
                                )
                            }
                            is app.te.network.utils.Resource.Failure -> {
                                _signUpState.value = SignUpResultState(
                                    failureStatus = resource
                                )
                            }
                            is app.te.network.utils.Resource.Loading -> {
                                _signUpState.value = SignUpResultState(
                                    isLoading = true
                                )
                            }
                            else -> {}
                        }
                    }
            }
    }

    private fun validateInputs(): Boolean {
        val governResult = validateField.invoke(state.govern)
        val cityResult = validateField.invoke(state.city)
        val nameResult = validateField.invoke(state.name)
        val addressResult = validateField.invoke(state.address)
        val phoneResult = validatePhone.invoke(state.phone)
        val passwordResult = validatePassword.invoke(state.password)

        val hasError = listOf(
            governResult, cityResult, nameResult, phoneResult, passwordResult, addressResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                governError = governResult.errorMessage,
                cityError = cityResult.errorMessage,
                nameError = nameResult.errorMessage,
                phoneError = phoneResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                addressError = addressResult.errorMessage
            )
            return false
        }
        return true
    }

//    fun updateGovern(government: Government) {
//        state = state.copy(
//            govern = government.title,
//            governId = government.id.toString(),
//            governError = null
//        )
//    }
//
//    fun updateCity(city: CityModel) {
//        state = state.copy(city = city.name, cityId = city.id.toString(), cityError = null)
//    }

}