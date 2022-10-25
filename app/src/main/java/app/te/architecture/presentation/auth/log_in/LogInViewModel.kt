package app.te.architecture.presentation.auth.log_in

import android.util.Log
import androidx.lifecycle.viewModelScope
import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.auth.entity.request.LogInRequest
import app.te.architecture.domain.auth.use_case.LogInUseCase
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
  private val logInUseCase: LogInUseCase
) : BaseViewModel() {

  var request = LogInRequest()
  private val _logInResponse =
    MutableStateFlow<Resource<BaseResponse<UserResponse>>>(Resource.Default)
  val logInResponse = _logInResponse


  fun onLogInClicked() {
    logInUseCase(request)
      .catch { exception ->
        Log.e(
          "onLogInClicked",
          "onLogInClicked: ${exception.printStackTrace()}"
        )
      }
      .onEach { result ->
        _logInResponse.value = result
      }
      .launchIn(viewModelScope)
  }



}