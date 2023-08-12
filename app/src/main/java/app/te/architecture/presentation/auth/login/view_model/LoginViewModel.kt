package app.te.architecture.presentation.auth.login.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.architecture.presentation.auth.login.events.LoginFormEvent
import app.te.architecture.presentation.auth.login.state.LoginState
import app.te.architecture.presentation.auth.login.state.LoginFormState
import app.te.architecture.domain.auth.entity.request.LogInRequest
import app.te.architecture.domain.auth.use_case.LogInUseCase
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.validation_usecase.ValidatePassword
import app.te.architecture.presentation.base.validation_usecase.ValidatePhone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val validatePhone: ValidatePhone,
    private val validatePassword: ValidatePassword
) : ViewModel() {

    var state by mutableStateOf(LoginFormState())

    private val _loginState =
        MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.PhoneChanged -> {
                state = state.copy(
                    phone = event.phone,
                    phoneError = validatePhone.invoke(event.phone).errorMessage
                )
            }
            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password,
                    passwordError = validatePassword.invoke(event.password).errorMessage
                )
            }
            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        if (validateInputs())
            viewModelScope.launch {
                logInUseCase.invoke(LogInRequest(phone = state.phone, password = state.password))
                    .collect { resource ->
                        when (resource) {
                            is Resource.Success -> {
                                _loginState.value = LoginState(
                                    data = resource.value.data
                                )
                            }
                            is Resource.Failure -> {
                                _loginState.value = LoginState(
                                    failureStatus = resource
                                )
                            }
                            is Resource.Loading -> {
                                _loginState.value = LoginState(
                                    isLoading = true
                                )
                            }
                            else -> {}
                        }
                    }
            }
    }

    private fun validateInputs(): Boolean {
        val phoneResult = validatePhone.invoke(state.phone)
        val passwordResult = validatePassword.invoke(state.password)

        val hasError = listOf(
            phoneResult, passwordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                phoneError = phoneResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return false
        }
        return true
    }
}
