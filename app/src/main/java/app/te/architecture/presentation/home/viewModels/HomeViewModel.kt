package app.te.architecture.presentation.home.viewModels

import android.content.Context
import androidx.lifecycle.viewModelScope
import app.te.architecture.core.notifications.notification_manager.FCMManager
import app.te.architecture.domain.account.use_case.UserLocalUseCase
import app.te.architecture.domain.auth.entity.model.UserResponse
import app.te.architecture.domain.general.entity.UpdateFirebaseTokenRequest
import app.te.architecture.domain.general.use_case.UpdateFirebaseTokenUseCase
import app.te.architecture.domain.home.models.HomeData
import app.te.architecture.domain.home.use_case.HomeUseCase
import app.te.architecture.domain.utils.BaseResponse
import app.te.architecture.domain.utils.Resource
import app.te.architecture.presentation.base.BaseViewModel
import app.te.architecture.presentation.base.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    val userLocalUseCase: UserLocalUseCase,
    private val updateFirebaseTokenUseCase: UpdateFirebaseTokenUseCase
) : BaseViewModel() {
    private val _homeResponse =
        MutableStateFlow<Resource<BaseResponse<HomeData>>>(Resource.Default)
    val homeResponse = _homeResponse
    val updateToken = MutableStateFlow(false)

    fun getHomeData(cat_id: Int) {
        homeUseCase.homeService(cat_id)
            .onEach { result ->
                _homeResponse.value = result
            }
            .launchIn(viewModelScope)
    }

    fun updateUser(subscriber: Int) {
        viewModelScope.launch {
            userLocalUseCase.invoke().collect { user ->
                if (user.name.isNotEmpty() && user.subscriber == Constants.FREE) {
                    val userResponse =
                        UserResponse(
                            id = user.id,
                            email = user.email,
                            name = user.name,
                            image = user.image,
                            phone = user.phone,
                            jwt = user.jwt,
                            city_id = user.cityId,
                            city_name = user.cityName,
                            subscriber = subscriber
                        )
                    userLocalUseCase.invoke(userResponse)
                    updateToken.value = true
                }
            }
        }
    }

    fun updateFireBaseToken(context: Context) {
//        FCMManager.generateFCMToken(context) { token ->
//            updateFirebaseToken(token)
//        }
    }

    private fun updateFirebaseToken(result: String) {
        viewModelScope.launch {
            updateFirebaseTokenUseCase.invoke(UpdateFirebaseTokenRequest(result)).collectLatest {
            }
        }
    }
}