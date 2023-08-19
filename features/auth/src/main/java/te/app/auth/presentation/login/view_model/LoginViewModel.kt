package te.app.auth.presentation.login.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.te.auth.AuthenticationDirections
import te.app.auth.presentation.login.events.LoginFormEvent
import te.app.auth.presentation.login.state.LoginState
import te.app.auth.presentation.login.state.LoginFormState
import app.te.core.validation_usecase.ValidatePassword
import app.te.core.validation_usecase.ValidatePhone
import app.te.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import te.app.auth.domain.entity.request.LogInRequest
import te.app.auth.domain.use_case.LogInUseCase
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val validatePhone: ValidatePhone,
    private val validatePassword: ValidatePassword,
    private val navigationManager: NavigationManager
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

            is LoginFormEvent.Login -> {
                submitData()
            }

            is LoginFormEvent.SignUp -> {
                openSignScreen()
            }

            is LoginFormEvent.ResetPassword -> {
                openResetPasswordScreen()
            }

            else -> {}
        }
    }

    private fun openResetPasswordScreen() {
        viewModelScope.launch {
            navigationManager.navigate(AuthenticationDirections.VerifyScreen())
        }
    }

    private fun openSignScreen() {
        viewModelScope.launch {
            navigationManager.navigate(AuthenticationDirections.SignUpScreen())
        }
    }

    private fun submitData() {
        if (validateInputs())
            viewModelScope.launch {
                logInUseCase.invoke(LogInRequest(phone = state.phone, password = state.password))
                    .collect { resource ->
                        when (resource) {
                            is app.te.network.utils.Resource.Success -> {
                                _loginState.value = LoginState(
                                    data = resource.value.data
                                )
                            }

                            is app.te.network.utils.Resource.Failure -> {
                                _loginState.value = LoginState(
                                    failureStatus = resource
                                )
                            }

                            is app.te.network.utils.Resource.Loading -> {
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
