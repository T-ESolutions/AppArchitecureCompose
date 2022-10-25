package app.te.architecture.presentation.auth.confirmCode

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.auth.entity.request.ForgetPasswordRequest
import app.te.architecture.domain.auth.entity.request.RegisterRequest
import app.te.architecture.domain.auth.use_case.ResendUseCase
import app.te.architecture.domain.auth.use_case.VerifyAccountUseCase
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseViewModel
import app.te.architecture.presentation.base.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(
    private val verifyAccountUseCase: VerifyAccountUseCase,
    private val resendUseCase: ResendUseCase,
    val savedStateHandle: SavedStateHandle
) :
    BaseViewModel() {
    var request = RegisterRequest()
    var forgetRequest = ForgetPasswordRequest()

    private val _verifyResponse =
        MutableStateFlow<Resource<BaseResponse<UserResponse>>>(Resource.Default)
    val verifyResponse = _verifyResponse
    private val _verifyForgetResponse =
        MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
    val verifyForgetResponse = _verifyForgetResponse
    private val _resendResponse = MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
    val resendResponse = _resendResponse

    init {

        savedStateHandle.get<RegisterRequest>("user_request")?.let { user_request ->
            request = user_request

            /**
             * filling for resend code
             */
            forgetRequest.phone = user_request.phone
            forgetRequest.city_id = user_request.city_id
            forgetRequest.email =
                if (user_request.city_id != Constants.EGYPT_ID) user_request.email else null
        }

        savedStateHandle.get<ForgetPasswordRequest>("forget_request")?.let { forget_request ->
            forgetRequest = forget_request
        }

    }

    fun verifyAccount() {
        if (forgetRequest.phone.isEmpty())
            verifyAccountUseCase(request)
                .onEach { result ->
                    _verifyResponse.value = result
                }
                .launchIn(viewModelScope)
        else {
            forgetRequest.code = request.otp
            verifyAccountUseCase.invoke(forgetRequest)
                .onEach { result ->
                    _verifyForgetResponse.value = result
                }
                .launchIn(viewModelScope)
        }
    }

    fun resendCode() {
        resendUseCase(forgetRequest)
            .onEach { result ->
                _resendResponse.value = result
            }
            .launchIn(viewModelScope)
    }

}