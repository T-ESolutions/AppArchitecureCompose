package te.app.auth.presentation.sign_up.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.auth.LOGIN_ROUTE
import app.te.auth.LoginScreenNav
import te.app.auth.presentation.sign_up.events.SignUpFormEvent
import te.app.auth.presentation.sign_up.state.SignUpFormState
import te.app.auth.presentation.sign_up.state.SignUpResultState
import app.te.core.validation_usecase.ValidateField
import app.te.core.validation_usecase.ValidatePassword
import app.te.core.validation_usecase.ValidatePhone
import app.te.navigation.NavigationManager
import app.te.navigation.NavigationOptions
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
    private val validateField: ValidateField,
    private val navigationManager: NavigationManager
) : ViewModel() {

    var state by mutableStateOf(SignUpFormState())
    private val _signUpState =
        MutableStateFlow(SignUpResultState())
    val signUpState = _signUpState.asStateFlow()


    fun onEvent(event: SignUpFormEvent) {
        when (event) {
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

            is SignUpFormEvent.IdentityChanged -> {
                state = state.copy(
                    identity = event.identity,
                    identityError = validateField.invoke(event.identity).errorMessage
                )
            }

            is SignUpFormEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password,
                    passwordError = validatePassword.invoke(event.password).errorMessage
                )
            }

            is SignUpFormEvent.GenderChanged -> {
                state = state.copy(
                    gender = event.gender
                )
            }

            is SignUpFormEvent.Back -> {
                onBackPressed()
            }

            is SignUpFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun onBackPressed() {
        viewModelScope.launch {
            navigationManager.navigate(
                LoginScreenNav(
                    NavigationOptions(
                        popUpTo = LOGIN_ROUTE
                    )
                )
            )
        }
    }

    private fun submitData() {
        if (validateInputs())
            viewModelScope.launch {
                registerUseCase.invoke(
                    RegisterRequest(
                        name = state.name,
                        phone = state.phone,
                        password = state.password,
                        gender = state.gender,
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
        val nameResult = validateField.invoke(state.name)
        val phoneResult = validatePhone.invoke(state.phone)
        val passwordResult = validatePassword.invoke(state.password)

        val hasError = listOf(
            nameResult, phoneResult, passwordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                nameError = nameResult.errorMessage,
                phoneError = phoneResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return false
        }
        return true
    }

}