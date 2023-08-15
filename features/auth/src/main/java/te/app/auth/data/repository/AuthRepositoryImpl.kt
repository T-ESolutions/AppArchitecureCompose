package te.app.auth.data.repository

import app.te.network.utils.BaseResponse
import app.te.network.utils.Resource
import te.app.auth.data.data_source.remote.AuthRemoteDataSource
import te.app.auth.domain.entity.model.AppTutorialModel
import te.app.auth.domain.entity.model.UserResponse
import te.app.auth.domain.repository.AuthRepository
import te.app.auth.domain.entity.request.ForgetPasswordRequest
import te.app.auth.domain.entity.request.LogInRequest
import te.app.auth.domain.entity.request.RegisterRequest
import te.app.auth.domain.entity.request.UpdatePassword
import te.app.storage.data_store.AppPreferences
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val appPreferences: AppPreferences
) : AuthRepository {

    override
    suspend fun logIn(request: LogInRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>> {
        val response = remoteDataSource.logIn(request)
        if (response is app.te.network.utils.Resource.Success) {
            appPreferences.userToken(response.value.data.accessToken)
            //TODO UN COMMENT
//            appPreferences.saveUser(response.value.data)
        }
        return response
    }

    override suspend fun signUp(request: RegisterRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>> {
        val response = remoteDataSource.register(request)
        if (response is app.te.network.utils.Resource.Success) {
            appPreferences.userToken(response.value.data.accessToken)
            //TODO UN COMMENT
//            appPreferences.saveUser(response.value.data)
        }
        return response
    }

    override suspend fun changePassword(request: UpdatePassword): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>> =
        remoteDataSource.changePassword(request)

    override suspend fun authChangePassword(request: UpdatePassword): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>> =
        remoteDataSource.authChangePassword(request)

    override suspend fun forgetPassword(request: ForgetPasswordRequest) =
        remoteDataSource.forgetPassword(request)

    override suspend fun resendCode(request: ForgetPasswordRequest) =
        remoteDataSource.resendCode(request)


    override suspend fun verifyAccount(request: RegisterRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<UserResponse>> {
        val response = remoteDataSource.verifyAccount(request)
        if (response is app.te.network.utils.Resource.Success) {
            appPreferences.userToken(response.value.data.accessToken)
            //TODO UN COMMENT
//            appPreferences.saveUser(response.value.data)
        }
        return response
    }


    override suspend fun verifyPasswordAccount(request: ForgetPasswordRequest): app.te.network.utils.Resource<app.te.network.utils.BaseResponse<*>> =
        remoteDataSource.verifyPasswordAccount(request)

    override suspend fun intro(): Resource<BaseResponse<List<AppTutorialModel>>> =
        remoteDataSource.intro()

}

